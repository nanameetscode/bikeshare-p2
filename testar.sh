#!/usr/bin/env bash
# Compila o projeto e roda os scripts de aceitacao informados (ex.: ./testar.sh us1 us1_1).
# Sem argumentos, roda todos pela Main.
set -e
cd "$(dirname "$0")"
SEP=":"; case "$(uname -s)" in MINGW*|MSYS*|CYGWIN*) SEP=";";; esac
CP="out${SEP}lib/easyaccept.jar"
rm -rf out && mkdir -p out
javac -encoding UTF-8 -Xlint:all -d out -cp lib/easyaccept.jar $(find src -name '*.java')
if [ $# -eq 0 ]; then
    java -cp "$CP" Main
else
    for t in "$@"; do
        java -cp "$CP" easyaccept.EasyAccept br.ufal.ic.p2.bikeshare.Facade "tests/$t.txt"
    done
fi
