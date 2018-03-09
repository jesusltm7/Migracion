 package com.bod;
 
 public class SingletonController
 {
   private static SingletonController miconfigurador;
   private boolean procesoActivo;
   
   public static SingletonController getInstance() {
     if (miconfigurador == null) {
       miconfigurador = new SingletonController();
     }
     return miconfigurador;
   }
   
   public SingletonController() {
     this.procesoActivo = false;
   }
   
   public void tomarControl() {
     this.procesoActivo = true;
   }
   
   public void liberarControl() {
     this.procesoActivo = false;
   }
   
   public boolean esActivo() {
     return this.procesoActivo;
   }
 }