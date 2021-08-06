��|T������c=iSF����� �a)����C�Bk�<Rs�!wd'������F+#�W��CZ����d'��bQ=��!�E���c:���d��E�ﻸEk��Gi�Ue&�k_A����\�j��r�r�#}����t�&���F�(K�jE�oSs���q��I.^�>m������iU�����[��������2.�f~���bz-S��\��n�,a:6��!���>�|*��H�����R	"��_m�+����*+�W\���i�n`��ש�A~��*5�:�HG�멙/�Fr������hHB���V{��Z��  �A��g�G�,MPo��Iդ��u\��DW���j+Q2��߷���Ѻ~�t%�ϗ�[v�ж�HGB+KZ���}"}��SVN���*V��C{ܡ*oFKk_]�$��z�"����2Ͻ�!%b�J�3����|&�2�^�e��W�nB��S��>�^�^/C����ѹ�H�'0��XDl"!�*OC:/�1/M� ��^�z���ݚ�~ŏX���C$/��gq�x�jĢ�/�Qcd��$.5��i��u�T��t^����6^�-�^�Ɲ���ob��W9}}Lz����/w����95����=�rM_ܔ��=վ���<�1�ڄ�/O�y����S�!�+i�����4� �UDf��~=��^��4�r����#�!+��#�7�A�����V�  �A��gz��G�n�eu/�/B���g%O����u�GU����A�B�Ol�C�^K�U���I8K����Ų3��5�2�M�\�w��&믢J�!���B��HRv�Y9�0��/h[r���U�+L���#��t�J~�&���	����1���S����Y�D��[�_}���^�R����,��                                                                                                           utton();
        addRemoveWordButton = new javax.swing.JButton();
        recoPropsButton = new javax.swing.JButton();
        synthPropsButton = new javax.swing.JButton();
        micTrainingButton = new javax.swing.JButton();
        audioPropsButton = new javax.swing.JButton();
        audioVolumeButton = new javax.swing.JButton();
        
        getContentPane().setLayout(new java.awt.GridLayout(4, 2));
        
        setTitle("Test Native User Interfaces");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        userTrainingButton.setText("USER_TRAINING_UI");
        userTrainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTrainingButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(userTrainingButton);
        
        recoProfileButton.setText("RECO_PROFILE_UI");
        recoProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recoProfileButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(recoProfileButton);
        
        addRemoveWordButton.setText("ADD_REMOVE_WORD_UI");
        addRemoveWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRemoveWordButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(addRemoveWordButton);
        
        recoPropsButton.setText("ENGINE_PROPS_UI (Reco)");
        recoPropsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recoPropsButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(recoPropsButton);
        
        synthPropsButton.setText("ENGINE_PROPS_UI (Synth)");
        synthPropsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                synthPropsButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(synthPropsButton);
        
        micTrainingButton.setText("MIC_TRAINING_UI");
        micTrainingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                micTrainingButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(micTrainingButton);
        
        audioPropsButton.setText("AUDIO_PROPS_UI");
        audioPropsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioPropsButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(audioPropsButton);
        
        audioVolumeButton.setText("AUDIO_VOL_UI");
        audioVolumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioVolumeButtonActionPerformed(evt);
            }
        });
        
        getContentPane().add(audioVolumeButton);
        
        pack();
    }//GEN-END:initComponents
    
    private void audioVolumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioVolumeButtonActionPerformed
        Component comp = synthProps.getControlComponent(CGEngineProperties.AUDIO_VOL_UI);
        if(comp != null)  comp.show();
        else  JOptionPane.showMessageDialog(this,"Interface not available");
    }//GEN-LAST:event_audioVolumeButtonActionPerformed
    
    private void micTrainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_micTrainingButtonActionPerformed
        Component comp = recProps.getControlComponent(CGEngineProperties.MIC_TRAINING_UI);
        if(comp != null)  comp.show();
        else  JOptionPane.showMessageDialog(this,"Interface not available");
    }//GEN-LAST:event_micTrainingButtonActionPerformed
    
    private void recoPropsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recoPropsButtonActionPerformed
        Component comp = recProps.getControlComponent(CGEngineProperties.ENGINE_PROPS_UI);
        if(comp != null)  comp.show();
        else  JOptionPane.showMessageDialog(this,"Interface not available");
    }//GEN-LAST:event_recoPropsButtonActionPerformed
    
    private void recoProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recoProfileButtonActionPerformed
        Component comp = recProps.getControlComponent(CGEngineProperties.RECO_PROFILE_UI);
        if(comp != null)  comp.show();
        else  JOptionPane.showMessageDialog(this,"Interface not available");
    }//GEN-LAST:event_recoProfileButtonActionPerformed
    
    private void audioPropsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioPropsButtonActionPerformed
        Component comp = synthProps.getControlComponent(CGEngineProperties.AUDIO_PROPS_UI);
        if(comp != null)  comp.show();
        else  JOptionPane.showMessageDialog(this,"Interface not available");
    }//GEN-LAST:event_audioPropsButtonActionPerformed
    
    private void synthPropsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_synthPropsButtonActionPerformed
        Component comp = synthProps.getControlComponent(CGEngineProperties.ENGINE_PROPS_UI);
        if(comp != null)  comp.show();
        else  JOptionPane.showMessageDialog(this,"Interface not available");
    }//GEN-LAST:event_synthPropsButtonActionPerformed
    
    private void addRemoveWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRemoveWordButtonActionPerformed
        try {
            Component comp = recProps.getControlComponent(CGEngineProperties.ADD_REMOVE_WORD_UI);
            if(comp != null)  comp.show();
            else  JOptionPane.showMessageDialog(this,"Interface not available");
            //rec.resume();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_addRemoveWordButtonActionPerformed
    
    private void userTrainingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTrainingButtonActionPerformed
        final Component comp = speakerManager.getControlComponent();
        if(comp != null)  {
            Thread thr = new Thread() {
                public void run() {
                    comp.show();
                }
            };
            thr.start();
        } else {
            JOptionPane.showMessageDialog(this,"Interface not available");
        }
    }//GEN-LAST:event_userTrainingButtonActionPerformed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        try {
            if(rec != null) {
                rec.deallocate();
                rec.waitEngineState(Engine.DEALLOCATED);
            }
            System.out.println("Rec Deallocated");
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            synth.deallocate();
            synth.waitEngineState(Engine.DEALLOCATED);
            System.out.println("Synth Deallocated");
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
    public void init() {
        try {
            EngineModeDesc desc = null;
            SpeechEngineChooser chooser = SpeechEngineChooser.getAllEnginesDialog();            
            chooser.show();
            desc = chooser.getRecognizerModeDesc();
            rec = Central.createRecognizer(desc);
            rec.allocate();
            rec.waitEngineState(Engine.ALLOCATED);
            
            System.out.println("recognizer allocated");
            
            //Current speaker needs to be set for the SAPI4 Voice-training dialog to appear
            speakerManager = rec.getSpeakerManager();
            speakerManager.setCurrentSpeaker(chooser.getSpeakerProfile());
            System.out.println("Set Current Profile to "+speakerManager.getCurrentSpeaker().getName());

            //cast into a CGEngineProperties so we can use the getControlComponent(int) method.
            recProps = (CGEngineProperties)rec.getEngineProperties();
            
            //Test out some of the RecognizerProperties
            //While it looks like setting and getting them work OK, these values do not
            //seem to be related to the ones on the speech control panel!
            ((RecognizerProperties)recProps).setConfidenceLevel(0.7f);
            ((RecognizerProperties)recProps).setSpeedVsAccuracy(0.9f);
            System.out.println("Speed vs Accuracy = "+((RecognizerProperties)recProps).getSpeedVsAccuracy());
            System.out.println("Confidence = "+((RecognizerProperties)recProps).getConfidenceLevel());
            System.out.println("Current SpeakerProfile="+speakerManager.getCurrentSpeaker().getName());
            
            desc = chooser.getSynthesizerModeDesc();
            synth = Central.createSynthesizer(desc);
            synth.allocate();
            synth.waitEngineState(Engine.ALLOCATED);
            System.out.println("synth allocated");
            //cast into a CGEngineProperties so we can use the getControlComponent(int) method.
            synthProps = (CGEngineProperties)synth.getEngineProperties();            
            ((SynthesizerProperties)synthProps).setVoice(chooser.getVoice());
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        TestInterfaces ti = new TestInterfaces();
        ti.init();
        ti.show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton userTrainingButton;
    private javax.swing.JButton recoProfileButton;
    private javax.swing.JButton addRemoveWordButton;
    private javax.swing.JButton recoPropsButton;
    private javax.swing.JButton synthPropsButton;
    private javax.swing.JButton micTrainingButton;
    private javax.swing.JButton audioPropsButton;
    private javax.swing.JButton audioVolumeButton;
    // End of variables declaration//GEN-END:variables
    private   Recognizer rec;
    private   Synthesizer synth;
    private CGEngineProperties recProps, synthProps;
    private SpeakerManager speakerManager;
}
