CREATE TABLE VEM_SER.ESTUDANTE (
	id_estudante NUMBER NOT NULL,
	nome VARCHAR2(200) NOT NULL,
	data_nascimento DATE NOT NULL,
	nr_matricula NUMBER(10) UNIQUE NOT NULL,
	ativo CHAR(1),
	PRIMARY KEY(id_estudante)
);


CREATE SEQUENCE VEM_SER.SEQ_ESTUDANTE
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

DROP SEQUENCE VEM_SER.SEQ_ESTUDANTE
DROP TABLE VEM_SER.ESTUDANTE


INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Paulo Joao', TO_DATE('10/02/2014', 'dd/mm/yyyy'), 4506896322, 'S');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Pedro Marcelo', TO_DATE('10/02/2013', 'dd/mm/yyyy'), 4586896111, 'N');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Felipe Paulo', TO_DATE('10/02/2011', 'dd/mm/yyyy'), 1186896361, 'S');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Lemos Paulo', TO_DATE('10/02/2010', 'dd/mm/yyyy'), 1586896329, 'N');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Pedro Andre', TO_DATE('10/02/2009', 'dd/mm/yyyy'), 4586896997, 'N');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Pedro Marcelo', TO_DATE('10/02/2003', 'dd/mm/yyyy'), 4586898888, 'N');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Fernando Paulo', TO_DATE('10/02/2002', 'dd/mm/yyyy'), 4580132221, 'S');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Leonardo Paulo', TO_DATE('10/02/2001', 'dd/mm/yyyy'), 4586896321, 'S');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Marcos Paulo', TO_DATE('10/02/1999', 'dd/mm/yyyy'), 9981396321, 'S');

INSERT INTO VEM_SER.ESTUDANTE (id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES(SEQ_ESTUDANTE.nextval, 'Claudio Gustavo', TO_DATE('10/02/1999', 'dd/mm/yyyy'), 5644396321, 'S');

SELECT * FROM VEM_SER.ESTUDANTE;



