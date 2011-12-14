; Assembly code para el programa "simple.glhbr"
MOV R0,#18 ; Cargamos 18
MOV R1,#3 ; Cargamos 3
DIV R0,R1     ; Dividimos
MOV R1,#1 ; Cargamos 1
ADD R0,R1 ; Sumamos
MOV x,R0    ; fin de la sentencia
; Rutina para mostrar el resultado de x
;        R = 7.0
