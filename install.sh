#!/bin/bash

# nom d'utilisateur
while [ -z "$user" ]
do
    user=$(whiptail --title "Connexion à la base de données" --inputbox "Veuillez entrer votre nom d'utilisateur" 10 50 3>&1 1>&2 2>&3)
    if [ $? -ne 0 ]
    then
        echo "Vous avez quittez l'installation de ASTRE"
        exit -1
    fi
done


# nom de la base de données
while [ -z "$bd" ]
do
    bd=$(whiptail --title "Connexion à la base de données" --inputbox "Veuillez entrer le nom de la base de données" 10 50 3>&1 1>&2 2>&3)
    if [ $? -ne 0 ]
    then
        echo "Vous avez quittez l'installation de ASTRE"
        exit -1
    fi
done



# mot de passe
while [ -z "$pwd" ]
do
    pwd=$(whiptail --title "Connexion à la base de données" --passwordbox "Veuillez entrer votre mot de passe" 10 50 3>&1 1>&2 2>&3)
    if [ $? -ne 0 ]
    then
        echo "Vous avez quittez l'installation de ASTRE"
        exit -1
    fi
done




# zone de d'installation de l'application
while [ -z "$install" ]
do
    install=$(whiptail --title "Installation de ASTRE" --inputbox "Veuillez entrer le chemin d'installation de ASTRE" 10 50 3>&1 1>&2 2>&3)
    if [ $? -ne 0 ]
    then
        echo "Vous avez quittez l'installation de ASTRE"
        exit -1
    fi
done

# installation de ASTRE
cd $install
mkdir ASTRE 
cd ASTRE
curl -O https://github.com/VicenteHugo/ASTRE/releases/download/v1.0.0/ASTRE.jar &
{
for ((i = 0 ; i <= 100 ; i += RANDOM % 15)); do
    sleep 0.2
    echo $i
done
} | whiptail --gauge "Installation de ASTRE" 6 50 0

echo "Installation de ASTRE terminée"

# ajout du fichier login.dat dans le dossier config
mkdir $install/ASTRE/config

echo "user=$user" > $install/ASTRE/config/login.dat
echo "bd=$bd" >> $install/ASTRE/config/login.dat
echo "pwd=$pwd" >> $install/ASTRE/config/login.dat

# création du raccourci sur le bureau
echo "[Desktop Entry]" > $HOME/Bureau/ASTRE.desktop
echo "Version=1.0" >> $HOME/Bureau/ASTRE.desktop
echo "Type=Application" >> $HOME/Bureau/ASTRE.desktop
echo "Name=ASTRE" >> $HOME/Bureau/ASTRE.desktop
echo "Comment=Application de gestion prévisuel" >> $HOME/Bureau/ASTRE.desktop
echo "Exec=$install/ASTRE/launch.sh" >> $HOME/Bureau/ASTRE.desktop
echo "Icon=$install/ASTRE/ASTRE.png" >> $HOME/Bureau/ASTRE.desktop
echo "Path=$install" >> $HOME/Bureau/ASTRE.desktop
echo "Terminal=false" >> $HOME/Bureau/ASTRE.desktop
echo "StartupNotify=false" >> $HOME/Bureau/ASTRE.desktop

chmod +x $HOME/Bureau/ASTRE.desktop

# ajout du fichier launch.sh pour lancer l'application
echo "#!/bin/bash" > $install/ASTRE/launch.sh
echo "java -jar ASTRE.jar" >> $install/ASTRE/launch.sh
chmod +x $install/ASTRE/launch.sh