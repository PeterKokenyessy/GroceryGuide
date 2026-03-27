#!/bin/bash
psql "postgresql://tesco_db_user:1Se90zNOSMBwbun01OSdh1aPapS73vZc@dpg-d6oucd75r7bs73f648d0-a.frankfurt-postgres.render.com:5432/tesco_db?sslmode=require" \
     -f seed_database.sql