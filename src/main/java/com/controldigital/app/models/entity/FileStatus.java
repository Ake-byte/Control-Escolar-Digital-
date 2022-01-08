package com.controldigital.app.models.entity;

/*
RED1: El usuario aún no ha subido un documento
RED2: No es el documento
RED3: No es legible
RED4: No está apostillado
YELLOW: En proceso de validación
GREEN:Archivo validado
 */

public enum FileStatus {

        RED1, RED2, RED3, RED4, YELLOW, GREEN;

}
