# -*- coding: utf-8 -*-

from datetime import date

hotels_map = {}
players_map = {}
referees_map = {}
games_chambers_map = {}
sales_chambers_map = {}

def countries_sql():
    insert_query_1 = 'INSERT INTO "PAIS" ("NOM") VALUES (\'%s\');'

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
        print insert_query_1 % ( name )

def hotels_sql():
    global hotels_map

    insert_query_1 = 'INSERT INTO "HOTEL" ("NOM") VALUES (\'%s\');'
    insert_query_2 = 'INSERT INTO "SALA" ("HOTEL", "NOM", "AFORAMENT") VALUES (\'%s\', \'%s\', \'%s\');'

    hotels = ( 'Hotel Antemare',
        'Hotel Caesar Palace',
        'Hotel Calipolis',
        'Hotel Capri',
        'Hotel Flamingo',
        'Hotel Hilton',
        'Hotel Holiday Inn',
        'Hotel Melià',
        'Hotel Miracle',
        'Hotel Montserrat',
        'Hotel Terramar' )

    chambers = ( "Gènova;Hotel Capri;25;1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8;25",
        "Venecia;Hotel Antemare;10;1.9,1.10,1.11,1.12,1.13,1.14,1.15,1.16;10",
        "Victòria;Hotel Montserrat;20;2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8;18",
        "Victòria;Hotel Calipolis;15;3.1,3.2,3.3,3.4;8",
        "Ducci;Hotel Montserrat;12;4.1,4.2;0",
        "Artemisa;Hotel Montserrat;30;5.1;0" )

    for i, name in enumerate(hotels, 1):
        print insert_query_1 % ( name )
        hotels_map[name] = name

    print

    for i, c in enumerate(chambers, 1):
        data = c.split(';')
        name = data[0]
        hotel_fk = hotels_map[data[1]]
        capacity = data[2]
        print insert_query_2 % ( hotel_fk, name, capacity )
        for i, gameday in enumerate(data[3].split(',')):
            if i == 0:
                sales_chambers_map[gameday] = data[4]
            else:
                sales_chambers_map[gameday] = 0
            games_chambers_map[gameday] = ( hotel_fk, name )

def vendors_sql():
    insert_query_1 = ( ''
            'INSERT INTO "TAQUILLER" ('
                '"DNI", '
                '"NOM", '
                '"TELEFON", '
                '"GENERE", '
                '"PAIS", '
                '"ENTRADES_VENUDES"'
            ') VALUES ('
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s'"
            ');'
        )

    vendors = ( "Maria,32013847R,Sra.,683372388",
        "Pere,38227338T,Sr.,633728377",
        "Gertrudis,43382738F,Sra.,602833740" )

    for i, v in enumerate(vendors, 100):
        data = v.split(',')
        name = data[0]
        dni = data[1]
        gender = data[2]
        ph = data[3]
        print insert_query_1 % ( dni, name, ph, gender, 'Espanya', 0 )

def referees_sql():
    global referees_map

    insert_query_1 = ( ''
            'INSERT INTO "JUTGE" ('
                '"DNI", '
                '"NOM", '
                '"TELEFON", '
                '"GENERE", '
                '"HOTEL", '
                '"PAIS", '
                '"TAULES", '
                '"VICTORIES_BLANQUES", '
                '"VICTORIES_NEGRES"'
            ') VALUES ('
                "'%s',"
                "'%s',"
                "NULL,"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s'"
            ');'
        )
    
    referees = ( "Benjumea Azlatiletamendi,20100393R,Sr.,França,Hotel Capri",
        "Illiech Vladimir,20200288L,Sra.,Estats Units,Hotel Capri",
        "Pérez Lucián,20300643G,Sr.,Rússia,Hotel Capri",
        "Roberto Mandini,20400453H,Sr.,Itàlia,Hotel Capri" )

    for i, v in enumerate(referees, 500):
        data = v.split(',')
        name = data[0]
        dni = data[1]
        gender = data[2]
        country = data[3]
        hotel = data[4]
        print insert_query_1 % ( dni, name, gender, hotel, country, 0, 0, 0 )

        referees_map[name] = dni

def players_sql():
    global players_map
    
    insert_query_1 = ( ''
            'INSERT INTO "JUGADOR" ('
                '"DNI", '
                '"NOM", '
                '"TELEFON", '
                '"GENERE", '
                '"HOTEL", '
                '"PAIS", '
                '"PARTIDES_PERDUDES", '
                '"PARTIDES_GUANYADES"'
            ') VALUES ('
                "'%s',"
                "'%s',"
                "NULL,"
                "'%s',"
                "%s,"
                "'%s',"
                "'%s',"
                "'%s'"
            ');'
        )

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
        name = data[0]
        dni = data[1]
        gender = data[2]
        country = data[3]
        age = data[4]
        hotel = data[5]
        if ( len(hotel) > 0 ):
            hotel = "'%s'" % hotel
        else:
            hotel = 'NULL'
        
        year_born = today.year - int(age)
        
        print insert_query_1 % ( dni, name, gender, hotel, country, 0, 0 )

        players_map[name] = dni

def gamedays_sql():
    insert_query_1 = 'INSERT INTO "JORNADA" ("ID", "DATA_REALITZACIO") VALUES (\'%s\', NULL);'
    gamedays = [i for i in range(1, 6)]
    for i, g in enumerate(gamedays):
        print insert_query_1 % ( g )

def games_sql():
    global players_map
    
    insert_query_1 = ( ''
            'INSERT INTO "PARTIDA" ('
                '"ID", '
                '"GUANYADOR", '
                '"JUTGE", '
                '"BLANQUES", '
                '"NEGRES", '
                '"JORNADA", '
                '"HOTEL", '
                '"SALA", '
                '"ENTRADES_VENUDES"'
            ') VALUES ('
                "'%s',"
                "NULL,"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s',"
                "'%s'"
            ');'
        )
    insert_query_2 = 'INSERT INTO "MOVIMENT" ("PARTIDA", "ORDRE", "DESCRIPCIO") VALUES (\'%s\', \'%s\', \'%s\');'

    games = ( "1.1;Bobby Fischer;Garry Kasparov;Benjumea Azlatiletamendi",
        "1.2;Mijail Tahl;Anatoli Karpov;Benjumea Azlatiletamendi ",
        "1.3;Mijail Botvínnik;Viswanathan Anand;Benjumea Azlatiletamendi",
        "1.4;Paul Keres;Topalov;Illiech Vladimir",
        "1.5;Wilhelm Steinitz;Maya Chiburdanidze;Benjumea Azlatiletamendi",
        "1.6;Charles Stanley; Paul Morphy;Benjumea Azlatiletamendi",
        "1.7;George Henry Mackenzie;S. Lipschütz;Benjumea Azlatiletamendi",
        "1.8;Magnus Carlsen;Boris Gelfand;Benjumea Azlatiletamendi",
        "1.9;Fabiano Caruana;Evgeny Alekseev;Benjumea Azlatiletamendi",
        "1.10;Alexander Morozevich;Étienne Bacrot;Illiech Vladimir",
        "1.11;Levon Aronian;Wang Hao;Illiech Vladimir",
        "1.12;Shakhriyar Mamedyarov;Viktor Laznicka;Illiech Vladimir",
        "1.13;Ray Robson;Larry Christiansen;Pérez Lucián",
        "1.14;Robert Hess;Anish Girl;Pérez Lucián",
        "1.15;Emanuel Lasker;Max Euwe;Pérez Lucián",
        "1.16;José Raúl Capablanca;Judit Polgar;Pérez Lucián",
        "2.1;Anatoli Karpov;Bobby Fischer;Roberto Mandini",
        "2.2;Viswanathan Anand; Topalov;Roberto Mandini",
        "2.3;Charles Stanley;Wilhelm Steinitz;Roberto Mandini",
        "2.4;S. Lipschütz;Boris Gelfand;Roberto Mandini",
        "2.5;Fabiano Caruana;Étienne Bacrot;Pérez Lucián",
        "2.6;Levon Aronian;Shakhriyar Mamedyarov;Illiech Vladimir",
        "2.7;Larry Christiansen;Anish Girl;Pérez Lucián",
        "2.8;Emanuel Lasker;Judit Polgar;Pérez Lucián",
        "3.1;Bobby Fischer;Viswanathan Anand;Pérez Lucián",
        "3.2;Charles Stanley;Boris Gelfand;Pérez Lucián",
        "3.3;Fabiano Caruana;Levon Aronian;Pérez Lucián",
        "3.4;Anish Girl;Judit Polgar;Roberto Mandini",
        "4.1;Boris Gelfand;Bobby Fischer;Roberto Mandini",
        "4.2;Judit Polgar;Fabiano Caruana;Pérez Lucián" )

    for i, g in enumerate(games, 1):
        data = g.split(';')
        gameday = data[0]
        gameday_fk = data[0].split('.')[0]
        white_fk = players_map[data[1].strip()]
        black_fk = players_map[data[2].strip()]
        referee_fk = referees_map[data[3].strip()]
        hotel_fk = games_chambers_map[gameday][0]
        chamber_fk = games_chambers_map[gameday][1]
        sales = sales_chambers_map[gameday]
        print insert_query_1 % ( i, referee_fk, white_fk, black_fk, gameday_fk, hotel_fk, chamber_fk, sales )
        # try:
        #     with open('./moves/' + gameday + '.txt') as file:
        #         moves = file.readlines()
        #         for j, m in enumerate(moves):
        #             m = m.strip()
        #             if len(m) > 0: print insert_query_2 % ( i, j, m )
        # except IOError:
        #     # print 'File not found "%s.txt"' % gameday
        #     pass

def all_insert_sql():
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
    gamedays_sql()
    print
    games_sql()
    print

if __name__ == '__main__':
    all_insert_sql()
