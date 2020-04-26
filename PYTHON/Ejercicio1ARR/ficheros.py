import os
import shutil


def menu():
    print("\r\nFICHEROS")
    print("1.Leer fichero de texto")
    print("2.Copiar fichero")
    print("3.Listar archivos de directorio")
    print("0.Salir")
    print("Escoja una opción: ")


def submenu():
    print("\r\nTipo de fichero a copiar")
    print("1.Fichero de texto")
    print("2.Fichero binario")
    print("0.Cancelar")
    print("Escoja una opción: ")


if __name__ == '__main__':
    while True:
        menu()
        option = input()
        if option == "0":
            exit(0)
        elif option == "1":
            print("Escriba la ruta del fichero: ")
            file = input()
            if os.path.isfile(file):
                reader = open(file)
                print(reader.read())
                reader.close()
            else:
                print("La ruta '" + file + "' no es un fichero")
        elif option == "2":
            while option != "0":
                submenu()
                option = input()
                if option == "1":
                    print("Escriba la ruta del fichero de origen: ")
                    file1 = input()
                    if os.path.isfile(file1):
                        pass
                    else:
                        print("La ruta '" + file1 + "' no es un fichero")
                        continue
                    print("Escriba la ruta del fichero de destino: ")
                    file2 = input()
                    shutil.copy(file1, file2)
                    break
                elif option == "2":
                    print("Escriba la ruta del fichero de origen: ")
                    file1 = input()
                    if os.path.isfile(file1):
                        pass
                    else:
                        print("La ruta '" + file1 + "' no es un fichero")
                        continue
                    print("Escriba la ruta del fichero de destino: ")
                    file2 = input()
                    shutil.copy(file1, file2)
                    break
                elif option == "0":
                    break
                else:
                    print("Opción no válida")
                    continue
        elif option == "3":
            print("Escriba la ruta del directorio: ")
            path = input()
            if os.path.isdir(path):
                file_list = os.scandir(path)
                for file in file_list:
                    if os.path.isfile(file):
                        print(file.name + " -- " + str("%.2f" % (os.path.getsize(file) / 1024)) + "KB")
            else:
                print("La ruta '" + path + "' no es un directorio")
        else:
            print("Opción no válida")
            continue
