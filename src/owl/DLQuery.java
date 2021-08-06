
package owl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxEditorParser;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.expression.OWLEntityChecker;
import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.expression.ShortFormEntityChecker;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.BidirectionalShortFormProvider;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;


public class DLQuery {
File file;
OWLOntologyManager manager;
OWLOntology ontology;
OWLReasoner reasoner;
ShortFormProvider shortFormProvider;
DLQueryPrinter dlQueryPrinter;
  
public DLQuery() {
        try {
            // Carga la ontologia.
            file = new File("domotic.owl");
            manager = OWLManager.createOWLOntologyManager();
            ontology = manager.loadOntologyFromOntologyDocument(file);

            System.out.println("Ontologia cargada: " + ontology.getOntologyID());
            // Genera el razonador
            reasoner = createReasoner(ontology);

            shortFormProvider = new SimpleShortFormProvider();
            // Crea la clase para imprimir los resultados
            dlQueryPrinter = new DLQueryPrinter(new DLQueryEngine(
                    reasoner, shortFormProvider), shortFormProvider);
            // Crea un ciclo esperando que el usuario ingrese una linea de comando
            //doQuery(dlQueryPrinter);
        } catch (OWLOntologyCreationException e) {
            System.out.println("No se pudo cargar la ontologia: " + e.getMessage());
        } 
    }
//Ejecuta la linea de comando para ingresar la consulta
  public void doQuery(String classExpression) {
            // Espera que el usuario ingrese la expresion
            System.out.println("Ingrese la expresion en sintaxis Manchester y presione enter (presione x para salir):");
            System.out.println("");
            //String classExpression = readInput();
            // Cheque si el usuario ingreso la x para salir
            if (classExpression.equalsIgnoreCase("x")) {
                return;
            }
            dlQueryPrinter.askQuery(classExpression.trim());
            System.out.println();
            System.out.println();
    }
//Lee la linea de comandos
    private static String readInput() throws IOException {
        InputStream is = System.in;
        InputStreamReader reader;
        reader = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        return br.readLine();
    }
// Genera una instancia del razonador para ingresar los querys
    private static OWLReasoner createReasoner(final OWLOntology rootOntology) {
        
        OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
        return reasonerFactory.createReasoner(rootOntology);
    }
}

/** Desarrolla un "dlquery". */
class DLQueryEngine {
    private final OWLReasoner reasoner;
    private final DLQueryParser parser;

/** Constructor del motor DLQueryEngine. Responde los querys ingresado por el usuario
*
* @param reasoner
* Razonador usuario para responder los querys.
* @param shortFormProvider
* Short form provider. */
    public DLQueryEngine(OWLReasoner reasoner, ShortFormProvider shortFormProvider) {
        this.reasoner = reasoner;
        OWLOntology rootOntology = reasoner.getRootOntology();
        parser = new DLQueryParser(rootOntology, shortFormProvider);
    }

/** Obtiene la superclasses.
*
* @param classExpressionString
* Expresion string
* @param direct
* Especifica si la superclase debe ser retornada.
* @return La superclase */
    public Set<OWLClass> getSuperClasses(String classExpressionString, boolean direct) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
        NodeSet<OWLClass> superClasses = reasoner.getSuperClasses(classExpression, direct);
        return superClasses.getFlattened();
    }

    /** Obtiene la clase equivalente.
*
* @param classExpressionString
* La cadena el cual va a ser analizada.
* @return La clase equivalente a la expresion */
    public Set<OWLClass> getEquivalentClasses(String classExpressionString) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
        Node<OWLClass> equivalentClasses = reasoner.getEquivalentClasses(classExpression);
        Set<OWLClass> result;
        if (classExpression.isAnonymous()) {
            result = equivalentClasses.getEntities();
        } else {
            result = equivalentClasses.getEntitiesMinus(classExpression.asOWLClass());
        }
        return result;
    }

/** Obtiene la superclase el el cual va a ser analizada.
*
* @param classExpressionString
* Cadena que va a ser analizada
* @param direct
* Especifica si la superclase se va a especificar
* @return la super clase si hubo un error */
    public Set<OWLClass> getSubClasses(String classExpressionString, boolean direct) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
        NodeSet<OWLClass> subClasses = reasoner.getSubClasses(classExpression, direct);
        return subClasses.getFlattened();
    }

/** Obtiene la instancia de la super clase especificada
*
* @param classExpressionString
* La cadena a ser analizada
* @param direct
* Especifica si la instancia debe ser analizada
* @return La instancia de la expresion dada */
    public Set<OWLNamedIndividual> getInstances(String classExpressionString,
            boolean direct) {
        if (classExpressionString.trim().length() == 0) {
            return Collections.emptySet();
        }
        OWLClassExpression classExpression = parser.parseClassExpression(classExpressionString);
        NodeSet<OWLNamedIndividual> individuals = reasoner.getInstances(classExpression, direct);
        return individuals.getFlattened();
    }
}

class DLQueryParser {
    private final OWLOntology rootOntology;
    private final BidirectionalShortFormProvider bidiShortFormProvider;

    /** Constructor de DLQueryParser usando la ontologia
*
* @param rootOntology
* La ruta de la ontologia
* vocabulario del query.
* @param shortFormProvider
* Short form provider */
    public DLQueryParser(OWLOntology rootOntology, ShortFormProvider shortFormProvider) {
        this.rootOntology = rootOntology;
        OWLOntologyManager manager = rootOntology.getOWLOntologyManager();
        Set<OWLOntology> importsClosure = rootOntology.getImportsClosure();
        
        bidiShortFormProvider = new BidirectionalShortFormProviderAdapter(manager,
                importsClosure, shortFormProvider);
    }

/** Analiza la expresion.
*
* @param classExpressionString
* La class expression string
* @return la exprexion correspondiente */
    public OWLClassExpression parseClassExpression(String classExpressionString) {
        OWLDataFactory dataFactory = rootOntology.getOWLOntologyManager().getOWLDataFactory();

        ManchesterOWLSyntaxEditorParser parser = new ManchesterOWLSyntaxEditorParser(dataFactory, classExpressionString);
        parser.setDefaultOntology(rootOntology);
        // Indica la entindad
        OWLEntityChecker entityChecker = new ShortFormEntityChecker(bidiShortFormProvider);
        parser.setOWLEntityChecker(entityChecker);
        try {
            // Ejecuta el analisis
            return parser.parseClassExpression();
        } catch (ParserException ex) {
            Logger.getLogger(DLQueryParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

class DLQueryPrinter {
    private final DLQueryEngine dlQueryEngine;
    private final ShortFormProvider shortFormProvider;

    /** @param engine
* El motor
* @param shortFormProvider
* Short form provider */
    public DLQueryPrinter(DLQueryEngine engine, ShortFormProvider shortFormProvider) {
        this.shortFormProvider = shortFormProvider;
        dlQueryEngine = engine;
    }

    /** @param classExpression
* La expresion para hacer las preguntas*/
    public void askQuery(String classExpression) {
        if (classExpression.length() == 0) {
            System.out.println("Sin especificacion de clases");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\n--------------------------------------------------------------\n");
            sb.append("QUERY: ");
            sb.append(classExpression);
            sb.append("\n");
            sb.append("-----------------------------------------------------------------\n\n");
            Set<OWLClass> superClasses = dlQueryEngine.getSuperClasses(classExpression, true);
            printEntities("SuperClasses", superClasses, sb);
            Set<OWLClass> equivalentClasses = dlQueryEngine.getEquivalentClasses(classExpression);
            printEntities("EquivalentClasses", equivalentClasses, sb);
            Set<OWLClass> subClasses = dlQueryEngine.getSubClasses(classExpression,true);
            printEntities("SubClasses", subClasses, sb);
            Set<OWLNamedIndividual> individuals = dlQueryEngine.getInstances(classExpression, true);
            printEntities("Instances", individuals, sb);
            System.out.println(sb.toString());
        }
    }

    private void printEntities(String name, Set<? extends OWLEntity> entities,
            StringBuilder sb) {
        sb.append(name);
        int length = 50 - name.length();
        for (int i = 0; i < length; i++) {
            sb.append(".");
        }
        sb.append("\n\n");
        if (!entities.isEmpty()) {
            for (OWLEntity entity : entities) {
                sb.append("\t");
                sb.append(shortFormProvider.getShortForm(entity));
                sb.append("\n");
            }
        } else {
            sb.append("\t[NINGUNA]\n");
        }
        sb.append("\n");
    }
}