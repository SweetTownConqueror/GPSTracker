import socket

##FONCTIONS##
def line_prepender(filename, text):
    #We read the existing text from file in READ mode
    src=open(filename,"r")
    fline=text    #Prepending string
    oline=src.readlines()
    #Here, we prepend the string we want to on first line
    oline.insert(0,fline)
    src.close()
     
    #We again open the file in WRITE mode 
    src=open(filename,"w")
    src.writelines(oline)
    src.close()

##SERVEUR##
ADRESSE = '192.168.1.23'
PORT = 6789

serveur = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serveur.bind((ADRESSE, PORT))
serveur.listen(1)
client, adresseClient = serveur.accept()
print 'Connexion de ', adresseClient

b = True
while b:
  donnees = client.recv(1024)
  if not donnees:
    print 'Erreur de reception.'
  else:
    if "quit_server" in donnees:
      b  = False
    else:
      print 'Reception de:' + donnees
      if "gps_location:" in donnees:
        line_prepender("loc.txt", donnees.split("::")[1])
      reponse = donnees.upper()
      print 'Envoi de :' + reponse
      n = client.send(reponse)
      if (n != len(reponse)):
        print 'Erreur envoi.'
      else:
        print 'Envoi ok.'


print 'Fermeture de la connexion avec le client.'
client.close()
print 'Arret du serveur.'
serveur.close()
