package com.controldigital.app.models.entity;

/**
 * Enum que representa el estatus de validación de un archivo
 */
public enum FileStatus {

        /**
         * RED1: El usuario aún no ha subido un documento
         * RED2: No es el documento
         * RED3: No es legible
         * RED4: No está apostillado
         * YELLOW: En proceso de validación
         * GREEN:Archivo validado
         */

        RED1, RED2, RED3, RED4, YELLOW, GREEN;

}
