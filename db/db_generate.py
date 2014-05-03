# -*- coding: utf-8 -*-

from db_inserts import all_insert_sql

def print_file(path):
    with open(path) as handle:
        for line in handle.readlines():
            print line,

def generate():
    print_file('tables.sql')
    print_file('functions.sql')
    print_file('triggers.sql')
    print
    all_insert_sql()

if __name__ == '__main__':
    generate()
