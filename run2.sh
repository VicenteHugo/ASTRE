#!/bin/bash

# Nom du fichier source C (sans l'extension)
source_file="src/Controleur.java"

# Nom de l'exécutable
executable="compilation.sh"

# Compilation du fichier source
echo "Compilation en cours..."
gcc -o $executable $source_file.c

# Vérification de la réussite de la compilation
if [ $? -eq 0 ]; then
    echo "Compilation réussie."

    # Lancement de l'application
    echo "Lancement de l'application..."
    ./$executable
else
    echo "Échec de la compilation. Veuillez corriger les erreurs avant de relancer le script."
fi
