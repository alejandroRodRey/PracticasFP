class CuentaBancaria:
    def ingresar_dinero(self, cantidad):
        pass

    def retirar_dinero(self, cantidad):
        pass

    def consultar_saldo(self):
        pass


class CuentaNormal(CuentaBancaria):
    def __init__(self, saldo):
        self.saldo = saldo

    def ingresar_dinero(self, cantidad):
        if cantidad <= 0:
            print("La cantidad debe ser positiva")
        else:
            self.saldo += cantidad

    def retirar_dinero(self, cantidad):
        if cantidad <= 0:
            print("La cantidad debe ser positiva")
        else:
            if (self.saldo - cantidad) < 0:
                print("Una Cuenta Normal no permite tener saldo negativo")
            else:
                self.saldo -= cantidad

    def consultar_saldo(self):
        return self.saldo


class CuentaPremium(CuentaBancaria):

    def __init__(self, saldo):
        self.saldo = saldo

    def ingresar_dinero(self, cantidad):
        if cantidad <= 0:
            print("La cantidad debe ser positiva")
        else:
            self.saldo += cantidad

    def retirar_dinero(self, cantidad):
        if cantidad <= 0:
            print("La cantidad debe ser positiva")
        else:
            self.saldo -= cantidad

    def consultar_saldo(self):
        return self.saldo
