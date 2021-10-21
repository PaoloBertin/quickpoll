INSERT INTO options(id, value)
    VALUES( 1, 'New England Patriots'),
          ( 2, 'Seattle Seahawks'    ),
          ( 3, 'Green Bay Packers'   ),
          ( 4, 'Denver Broncos'      ),
          ( 5, 'Internazionale FC'   ),
          ( 6, 'Milac AC'            ),
          ( 7, 'Juventus FC'         );

INSERT INTO votes(id, option_id)
    VALUES( 1, 1),
          ( 2, 1),
          ( 3, 4),
          ( 4, 2),
          ( 5, 3),
          ( 6, 2),
          ( 7, 3),
          ( 8, 4),
          ( 9, 1),
          (10, 5),
          (11, 5),
          (12, 7),
          (13, 7),
          (14, 5),
          (15, 6);

INSERT INTO polls(id, question)
    VALUES( 1, 'Who will win SuperBowl this year?'),
          ( 2, 'Who wins the football championship this year?')
    ;

INSERT INTO poll_option(poll_id, option_id)
    VALUES( 1, 1),
          ( 1, 2),
          ( 1, 3),
          ( 1, 4),
          ( 2, 5),
          ( 2, 6),
          ( 2, 7);