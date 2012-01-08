; Código ensamblador para el programa "ejemplo.glhbr"
MOV R0,#1 ; Cargamos 1
MOV R1,#2 ; Cargamos 2
MUL R0,R1     ;  Multiplicamos
MOV R1,#3 ; Cargamos 3
ADD R0,R1 ; Sumamos
MOV R1,#1023 ; Cargamos 1023
SUB R0,R1        ; Restamos
MOV myInt,R0    ; fin de la sentencia de asignación
; Rutina para mostrar el resultado de myInt
;        R = -1018.0
