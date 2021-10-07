# Razbezhkin_AV_Java_2021_Liga

Создание контенера с Postgre в Docker

docker run --name selectel -p 5432:5432 -e POSTGRES_USER=selectel -e POSTGRES_PASSWORD=selectel -e POSTGRES_DB=selectel -d postgres:13.3 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres"
