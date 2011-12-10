; Assembly code para el programa "shit_3.glhbr"

MOV R0,#2 ; Cargamos la constante 2
MOV R1,#10 ; Cargamos la constante 10
MUL R0,R1     ;  Multiplicamos
MOV R1,#6 ; Cargamos la constante 6
MOV R2,#3 ; Cargamos la constante 3
SUB R1,R2        ; Restamos
ADD R0,R1 ; Sumamos
MOV b,R0    ; fin de la sentencia
; Rutina para mostrar el resultado de b
