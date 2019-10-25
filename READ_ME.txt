version1

Améliorations notables effectuées:
-lancage automatique au démarrage
-même quittée si les données gps sont activée et qu'il y a une connexion, l'app envoie ses coordonnées GPS

Améliorations à effectuer (idées):
- Démarrer directement le service au démarrage sans passer par une Activity qui nécessite une Interface graphique
- Nettoyer le code ( virer TcpClient, laisser Tcp_Client, ...)
- Eventuellement mettre une  icone
- Envoyer d'autres informations que les coordonnées GPS
- empecher la désinstallation de l'appli




Fonctionnement:
1) lancer le serveur python : python pythonserver.py
2) ssh -R 2000:192.168.1.VOTRE_IP:6789 serveo.net
3) activer votre localisation et lancer l'appli sur votre appareil android
4) les informations gps vont maintenant s'afficher tranquillement chez vous dans le fichier loc.txt


Cette app est pour le moment un trackeur GPS.
Lorsque l'appli est lancée, même en background, elle essaye de se connecter au serveur dont l'adresse est dans le code lorsque la connexion est établie et qu'il y a réception du signal GPS ,la date et les coordonnees gps de l'appareil android sont envoyées au serveur dont l'adresse est dans le code de l'appli.

Le serveur est un serveur python qui recoit ces infos vio le protocole TCP et les enregistre dans un fichier texte. Il peut être lancé depuis n'importe quel ordinateur
à noter que je n'ai pour le moment essayé le serveur que sous linux ubuntu

Une petite problématique qui s'est posée était de pouvoir recevoir les données directement chez soi. Plutôt que de configurer la box pour rediriger le flux tcp du port
qui est dans le code vers le serveur python, j'ai décidé de passer par un service externe: serveo.

serveo permet de faire le lien avec votre serveur local et l'appli qui peut être n'importe où dans le monde. pour se faire il vous faut ssh et lancer la commande suivante: ssh -R 2000:192.168.1.VOTRE_IP:6789 serveo.net
2000 est le port que vous utilisez  chez serveo (modifiable)
 serveo.net est le serveur ssh qui va rediriger notre flux tcp vers votre adresse locale (192.168.1.VOTRE_ADRESSEIP) et 6789 est le port de votre serveur local qui est dans pythonserver.py

plus d'infos sur serveo.net

