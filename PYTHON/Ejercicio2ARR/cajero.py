from cuentas import CuentaPremium, CuentaNormal


def menu():
    print("\r\nBANCO\r\n"
          "1. Crear cuenta\r\n"
          "2. Ingresar dinero\r\n"
          "3. Retirar dinero\r\n"
          "4. Consultar saldo\r\n"
          "0. Salir\r\n"
          "\r\n"
          "Escoja una opción: ")


def sub_menu():
    print("\r\nTipo de cuenta\r\n"
          "1. Cuenta normal\r\n"
          "2. Cuenta Premium\r\n"
          "0. Cancelar\r\n"
          "\r\n"
          "Escoja una opción: ")


if __name__ == '__main__':
    while True:
        menu()
        option = input()
        if option == "0":
            exit(0)
        elif option == "1":
            sub_menu()
            option = input()
            if option == "0":
                continue
            elif option == "1":
                while True:
                    try:
                        saldo = -1
                        while saldo < 0:
                            saldo = float(input("Escriba el saldo inicial: "))
                            if saldo < 0:
                                print("El saldo no puede ser negativo\r\n")
                        cuenta = CuentaNormal(saldo)
                        print("Creada Cuenta Normal con saldo de " + str(cuenta.consultar_saldo()) + "€")
                        break
                    except:
                        print("ERROR DE FORMATO, INTRODUZCA UN NUMERO VALIDO\r\n")
            elif option == "2":
                while True:
                    try:
                        saldo = float(input("Escriba el saldo inicial: "))
                        cuenta = CuentaPremium(saldo)
                        print("Creada Cuenta Premium con saldo de " + str(cuenta.consultar_saldo()) + "€")
                        break
                    except:
                        print("ERROR DE FORMATO, INTRODUZCA UN NUMERO VALIDO\r\n")
            else:
                print("Opción no valida")
                continue
        elif option == "2":
            try:
                cantidad = float(input("Escriba la cantidad: "))
                try:
                    cuenta.ingresar_dinero(cantidad)
                except:
                    print("No hay una cuenta activa")
                    continue
                print("El saldo de la cuenta es %.1f€" % cuenta.consultar_saldo())
            except:
                print("ERROR DE FORMATO, INTRODUZCA UN NUMERO VALIDO\r\n")
        elif option == "3":
            try:
                cantidad = float(input("Escriba la cantidad: "))
                try:
                    cuenta.retirar_dinero(cantidad)
                except:
                    print("No hay una cuenta activa")
                    continue
                print("El saldo de la cuenta es %.1f€" % cuenta.consultar_saldo())
            except:
                print("ERROR DE FORMATO, INTRODUZCA UN NUMERO VALIDO\r\n")
        elif option == "4":
            try:
                print("El saldo de la cuenta es %.1f€" % cuenta.consultar_saldo())
            except:
                print("No hay una cuenta activa")
                continue
        else:
            print("Opción no valida")
            continue
