# -*- coding: utf-8 -*-

from datetime import date

hotels_map = {}
players_map = {}
games_chambers_map = {}

def countries_sql():
    insert_query_1 = 'INSERT INTO "ESTAT" ("ID", "NOM", "CODI_ISO") VALUES (\'%s\', \'%s\', \'%s\');'

    countries = ( "Alemanya",
        "Anglaterra",
        "Espanya",
        "Estats Units",
        "França",
        "Gales",
        "Itàlia",
        "Polònia",
        "Rússia",
        "Xina",
        "Índia" )

    for i, name in enumerate(countries, 1):
        print insert_query_1 % ( i, name, '' )

def hotels_sql():
    global hotels_map

    insert_query_1 = 'INSERT INTO "HOTELS" ("ID", "NOM", "CIUTAT_ID") VALUES (\'%s\', \'%s\', \'%s\');'
    insert_query_2 = 'INSERT INTO "SALA" ("ID", "NOM", "AFORAMENT", "HOTEL_ID") VALUES (\'%s\', \'%s\', \'%s\', \'%s\');'

    hotels = ( "Hotel Capri",
        "Hotel Antemare",
        "Hotel Montserrat",
        "Hotel Calipolis" )

    chambers = ( "Gènova;Hotel Capri;25;1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8",
        "Venecia;Hotel Antemare;10;1.9,1.10,1.11,1.12,1.13,1.14,1.15,1.16",
        "Victòria;Hotel Montserrat;20;2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8",
        "Victòria;Hotel Calipolis;15;3.1,3.2,3.3,3.4,4.1,4.2,5.1" )

    for i, name in enumerate(hotels, 1):
        print insert_query_1 % ( i, name, '' )
        hotels_map[name] = i

    print

    for i, c in enumerate(chambers, 1):
        data = c.split(';')
        name = data[0]
        hotel_id = hotels_map[data[1]]
        capacity = data[2]
        print insert_query_2 % ( i, name, capacity, hotel_id )
        for gameday in data[3].split(','):
            games_chambers_map[gameday] = i

def vendors_sql():
    insert_query_1 = 'INSERT INTO "USUARI" ("ID", "NOM", "DNI", "SEXE", "TELEFON", "TIPUS_USUARI") VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');'
    insert_query_2 = 'INSERT INTO "USUARI_TAQUILLER" ("USUARI_ID", "ENTRADES_VENUDES") VALUES (\'%s\', \'%s\');'

    vendors = ( "Maria,32013847R,Sra.,683372388",
        "Pere,38227338T,Sr.,633728377",
        "Gertrudis,43382738F,Sra.,602833740" )

    for i, v in enumerate(vendors, 100):
        data = v.split(',')
        name = data[0]
        dni = data[1]
        sex = data[2]
        ph = data[3]
        if sex == 'Sr.': sex = 'M'
        elif sex == 'Sra.': sex = 'F'
        print insert_query_1 % ( i, name, dni, sex, ph, 'TAQUILLER' )
        print insert_query_2 % ( i, 0 )

def referees_sql():
    insert_query_1 = 'INSERT INTO "USUARI" ("ID", "NOM", "COGNOM", "DNI", "SEXE", "TIPUS_USUARI") VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');'
    insert_query_2 = 'INSERT INTO "USUARI_JUTGE" ("USUARI_ID", "VICTORIES_BLANQUES", "VICTORIES_NEGRES", "VICTORIES_TAULES") VALUES (\'%s\', \'%s\', \'%s\', \'%s\');'

    referees = ( "Benjumea Azlatiletamendi,20100393R,Sr.,França,Hotel Capri",
        "Illiech Vladimir,20200288L,Sra.,Estats Units,Hotel Capri",
        "Pérez Lucián,20300643G,Sr.,Rússia,Hotel Capri",
        "Roberto Mandini,20400453H,Sr.,Itàlia,Hotel Capri" )

    for i, v in enumerate(referees, 500):
        data = v.split(',')
        name = ''
        surname = ''
        name = data[0].split(" ")[0]
        if ( len(data[0].split(" ")) > 1 ):
            surname = data[0].split(" ")[1]
        dni = data[1]
        sex = data[2]
        country = data[3]
        if sex == 'Sr.': sex = 'M'
        elif sex == 'Sra.': sex = 'F'
        print insert_query_1 % ( i, name, surname, dni, sex, 'JUTGE' )
        print insert_query_2 % ( i, 0, 0, 0 )

def players_sql():
    global players_map
    
    insert_query_1 = 'INSERT INTO "USUARI" ("ID", "NOM", "COGNOMS", "DNI", "SEXE", "DATA_NAIXEMENT", "TIPUS_USUARI") VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\');'
    insert_query_2 = 'INSERT INTO "USUARI_JUGADOR" ("USUARI_ID", "PARTIDES_GUANYADES", "PARTIDES_PERDUDES") VALUES (\'%s\', \'%s\', \'%s\');'

    players = ( "Alexander Morozevich;11800;Sr.;Rússia;48;Hotel Capri;",
        "Anatoli Karpov;10300;Sr.;Rússia;38;Hotel Antemare;",
        "Anish Girl;12700123A;Sr.;Gales;60;Hotel Melià;",
        "Bobby Fischer;10000456H;Sr.;Estats Units;49;Hotel Hilton;",
        "Boris Gelfand;11500678S;Sr.;Anglaterra;25;Hotel Antemare;",
        "Charles Stanley;11000901D;Sr.;Estats Units;49;Hotel Calipolis;",
        "Emanuel Lasker;12800234A;Sr.;Estats Units;28;Hotel Caesar Palace;",
        "Étienne Bacrot;11900147M;Sra.;França;49;Hotel Flamingo;",
        "Evgeny Alekseev;11700269G;Sr.;Rússia;36;Hotel Flamingo;",
        "Fabiano Caruana;11600283R;Sr.;Itàlia;30;Hotel Holiday Inn;",
        "Garry Kasparov;10100383F;Sr.;Rússia;30;Hotel Capri;",
        "George Henry Mackenzie;11200393J;Sr.;Anglaterra;53;;",
        "José Raúl Capablanca;13000838P;Sr.;Espanya;46;Hotel Hilton;",
        "Judit Polgar;13100121L;Sra.;Anglaterra;58;Hotel Melià;",
        "Larry Christiansen;12500778U;Sr.;Estats Units;52;Hotel Caesar Palace;",
        "Levon Aronian;12000922R;Sr.;França;39;Hotel Antemare;",
        "Magnus Carlsen;11400212V;Sr.;Anglaterra;62;Hotel Terramar;",
        "Max Euwe;12900232W;Sr.;Anglaterra;39;Hotel Antemare;",
        "Maya Chiburdanidze;10900331F;Sra.;Rússia;62;Hotel Flamingo;",
        "Mijail Botvínnik;10400222T;Sr.;Rússia;59;;",
        "Mijail Tahl;10200442T;Sr.;Rússia;33;Hotel Miracle;",
        "Paul Keres;10600993N;Sr.;França;33;Hotel Terramar;",
        "Paul Morphy;11100199M;Sr.;Estats Units;42;Hotel Miracle;",
        "Ray Robson;12400229R;Sr.;Anglaterra;54;Hotel Holiday Inn;",
        "Robert Hess;12600221Y;Sr.;Estats Units;56;Hotel Terramar;",
        "S. Lipschütz;11300443Y;Sr.;Alemanya;41;Hotel Miracle;",
        "Shakhriyar Mamedyarov;12200333P;Sr.;Rússia;38;;",
        "Topalov;10700775L;Sr.;Rússia;56;Hotel Capri;",
        "Viktor Laznicka;12300335K;Sr.;Polònia;45;Hotel Montserrat;",
        "Viswanathan Anand;10500332J;Sr.;Índia;33;Hotel Montserrat;",
        "Wang Hao;12100887M;Sr.;Xina;59;Hotel Holiday Inn;",
        "Wilhelm Steinitz;10800332K;Sr.;Alemanya;35;;" )
    today = date.today()
    for i, p in enumerate(players, 1000):
        data = p.split(';')
        
        name = ''
        surname = ''
        name = data[0].split(" ")[0]
        if ( len(data[0].split(" ")) > 1 ):
            surname = data[0].split(" ")[1]
        
        dni = data[1]
        
        sex = data[2]
        if sex == 'Sr.': sex = 'M'
        elif sex == 'Sra.': sex = 'F'
        
        country = data[3]
        
        age = data[4]
        year_born = today.year - int(age)
        
        print insert_query_1 % ( i, name, surname, dni, sex, "%s-%s-%s" % ( year_born, today.month, today.day ), 'JUGADOR' )
        print insert_query_2 % ( i, 0, 0 )

        players_map[data[0]] = i

def games_sql():
    global players_map

    insert_query_1 = 'INSERT INTO "PARTIDA" ("ID", "JUGADOR_BLANQUES", "JUGADOR_NEGRES", "GUANYADOR", "VENCUT", "DURACIO", "JORNADA_ID", "SALA_ID") VALUES (\'%s\', \'%s\', \'%s\', NULL, NULL, \'%s\', \'%s\', \'%s\');'

    games = ( "1.1,Bobby Fischer,Garry Kasparov",
        "1.2,Mijail Tahl,Anatoli Karpov",
        "1.3,Mijail Botvínnik,Viswanathan Anand",
        "1.4,Paul Keres,Topalov",
        "1.5,Wilhelm Steinitz,Maya Chiburdanidze",
        "1.6,Charles Stanley,Paul Morphy",
        "1.7,George Henry Mackenzie,S. Lipschütz",
        "1.8,Magnus Carlsen,Boris Gelfand",
        "1.9,Fabiano Caruana,Evgeny Alekseev",
        "1.10,Alexander Morozevich,Étienne Bacrot",
        "1.11,Levon Aronian,Wang Hao",
        "1.12,Shakhriyar Mamedyarov,Viktor Laznicka",
        "1.13,Ray Robson,Larry Christiansen",
        "1.14,Robert Hess,Anish Girl",
        "1.15,Emanuel Lasker,Max Euwe",
        "1.16,José Raúl Capablanca,Judit Polgar",
        "2.1,Anatoli Karpov,Bobby Fischer",
        "2.2,Viswanathan Anand,Topalov",
        "2.3,Charles Stanley,Wilhelm Steinitz",
        "2.4,S. Lipschütz,Boris Gelfand",
        "2.5,Fabiano Caruana,Étienne Bacrot",
        "2.6,Levon Aronian,Shakhriyar Mamedyarov",
        "2.7,Larry Christiansen,Anish Girl",
        "2.8,Emanuel Lasker,Judit Polgar",
        "3.1,Bobby Fischer,Viswanathan Anand",
        "3.2,Charles Stanley,Boris Gelfand",
        "3.3,Fabiano Caruana,Levon Aronian",
        "3.4,Anish Girl,Judit Polgar",
        "4.1,Boris Gelfand,Bobby Fischer",
        "4.2,Judit Polgar,Fabiano Caruana" )

    for i, g in enumerate(games, 1):
        data = g.split(',')
        gameday_id = data[0].split('.')[0]
        white_id = players_map[data[1]]
        black_id = players_map[data[2]]
        chamber_id = games_chambers_map[data[0]]
        print insert_query_1 % ( i, white_id, black_id, 0, gameday_id, chamber_id )


if __name__ == '__main__':
    countries_sql()
    print
    hotels_sql()
    print
    vendors_sql()
    print
    referees_sql()
    print
    players_sql()
    print
    games_sql()
    print
