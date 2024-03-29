CREATE TABLE VEM_SER.PAIS (
    ID_PAIS INT PRIMARY KEY NOT NULL, -- Atalho PK
    NOME VARCHAR2(50) UNIQUE NOT NULL
);


CREATE TABLE VEM_SER.ESTADO (
    ID_ESTADO INT PRIMARY KEY NOT NULL,
    ID_PAIS INT NOT NULL,
    NOME VARCHAR2(50) UNIQUE NOT NULL
);


CREATE TABLE VEM_SER.CIDADE (
    ID_CIDADE INT NOT NULL,
    ID_ESTADO INT NOT NULL,
    ID_PAIS INT NOT NULL,
    NOME VARCHAR2(50) UNIQUE NOT NULL,
    CONSTRAINT PK_CIDADE PRIMARY KEY ( ID_CIDADE, ID_ESTADO ),
    CONSTRAINT FK_CIDADE_ESTADO FOREIGN KEY (ID_ESTADO) REFERENCES VEM_SER.ESTADO(ID_ESTADO)
);


CREATE TABLE VEM_SER.BAIRRO (
	ID_BAIRRO INT NOT NULL,
	ID_CIDADE INT NOT NULL,
    ID_ESTADO INT NOT NULL,
    NOME VARCHAR2(50) UNIQUE NOT NULL,
    CONSTRAINT PK_BAIRRO PRIMARY KEY ( ID_BAIRRO, ID_CIDADE ),
    CONSTRAINT FK_BAIRRO_CIDADE FOREIGN KEY ( ID_CIDADE, ID_ESTADO  ) REFERENCES VEM_SER.CIDADE( ID_CIDADE, ID_ESTADO ),
    CONSTRAINT FK_BAIRRO_ESTADO FOREIGN KEY ( ID_ESTADO ) REFERENCES VEM_SER.ESTADO( ID_ESTADO )
);


--CREATE TABLE VEM_SER.BAIRRO (
--	ID_BAIRRO INT NOT NULL,
--	ID_CIDADE INT NOT NULL,
--    ID_ESTADO INT NOT NULL,
--    NOME VARCHAR2(50) UNIQUE NOT NULL,
--    CONSTRAINT PK_BAIRRO PRIMARY KEY ( ID_BAIRRO, ID_CIDADE ),
--    CONSTRAINT FK_BAIRRO_CIDADE FOREIGN KEY ( ID_CIDADE, ID_ESTADO  ) REFERENCES CIDADE( ID_CIDADE, ID_ESTADO ),
--    CONSTRAINT FK_BAIRRO_ESTADO FOREIGN KEY ( ID_ESTADO ) REFERENCES ESTADO( ID_ESTADO )
--);

CREATE TABLE VEM_SER.ENDERECO (
	ID_ENDERECO NUMBER PRIMARY KEY NOT NULL,
	ID_BAIRRO NUMBER NOT NULL,
	ID_CIDADE NUMBER NOT NULL,
	LOGRADOURO VARCHAR2(255) NOT NULL,
	NUMERO NUMBER(38,0) NOT NULL,
	COMPLEMENTO VARCHAR2(100),
	CEP CHAR(9),
	CONSTRAINT FK_ENDERECO_BAIRRO FOREIGN KEY (ID_BAIRRO, ID_CIDADE) REFERENCES VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE)
);

CREATE SEQUENCE VEM_SER.SEQ_PAIS
START WITH 6
INCREMENT BY 17
NOCACHE NOCYCLE;

CREATE SEQUENCE VEM_SER.SEQ_ESTADO
START WITH 20
INCREMENT BY 5
NOCACHE NOCYCLE;

CREATE SEQUENCE VEM_SER.SEQ_ENDERECOS
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

CREATE SEQUENCE VEM_SER.SEQ_BAIRRO
START WITH 543
INCREMENT BY 3
NOCACHE NOCYCLE;

CREATE SEQUENCE VEM_SER.SEQ_CIDADE
START WITH 1000
INCREMENT BY 7
NOCACHE NOCYCLE;

CREATE SEQUENCE VEM_SER.SEQ_ENDERECO_BAIRRO
START WITH 1
INCREMENT BY 13
NOCACHE NOCYCLE;



-- INSERINDO PAISES
INSERT INTO VEM_SER.PAIS (ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_PAIS.nextval, 'Brasil')
INSERT INTO VEM_SER.PAIS (ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_PAIS.nextval, 'Colombia')

-- INSERINDO ESTADOS
INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_ESTADO.nextval, 6, 'Espirito Santo');
INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_ESTADO.nextval, 6, 'Sao Paulo')
INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_ESTADO.nextval, 23, 'Mississipi')
INSERT INTO VEM_SER.ESTADO (ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_ESTADO.nextval, 23, 'Massachussets')

-- INSERINDO CIDADES
INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 20, 6, 'Cachoeiro De Itapemirim');
INSERT INTO VEM_SER.CIDADE  (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 20, 6, 'Vitoria')
INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 25, 6, 'Osasco')
INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 25, 6, 'Sao Carlos')

INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 30, 23, 'Oxford')
INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 30, 23, 'Jackson')
INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 35, 23, 'Cambridge')
INSERT INTO VEM_SER.CIDADE (ID_CIDADE, ID_ESTADO, ID_PAIS, NOME)
VALUES (VEM_SER.SEQ_CIDADE.nextval, 35, 23, 'Boston')


-- INSERINDO BAIRROS

-- BAIRROS DO BRASIL
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1070, 20, 'Campo Leopoldina');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1070, 20, 'Alto Uniao');

INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1077, 20, 'Jardim Camburi');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1077, 20, 'Santo Antonio');

INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1084, 25, 'Centro');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1084, 25, 'Jaguaribe');

INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1091, 25, 'Jardim Lutfalla');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1091, 25, 'Jardim Centenario');

-- BAIRROS DOS EUA

INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1098, 30, 'Browsgrof');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1098, 30, 'Guttentaghj');

INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1105, 30, 'Marylin');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1105, 30, 'Emmejackson');


INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1112, 35, 'Lurrfoy');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1112, 35, 'Draco Malfoy');

INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1119, 35, 'Franksinat');
INSERT INTO VEM_SER.BAIRRO (ID_BAIRRO, ID_CIDADE, ID_ESTADO, NOME)
VALUES (VEM_SER.SEQ_BAIRRO.nextval, 1119, 35, 'Travelorws');


-- INSERINDO ENDEREÇOS

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 130, 1070, 'Rua firinfinfom', 589, 'Ao lado da rua 12', '29350-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 130, 1070, 'Rua firinfinfUNDO', 5854, 'Em cima da casa 11', '29354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 133, 1070, 'Rua marcao do dez', 5854, 'No bloco 4', '30354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 133, 1070, 'Rua luizin do trap', 14578, 'Em cima do lado b', '31354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 136, 1077, 'Sendo sincero', 5859, 'Em cima da casinha 14', '32354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 136, 1077, 'Ousl seart', 1004, 'Por de cima do lado de tras', '33354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 139, 1077, 'Carlet Maruilpe', 1004, 'Por de cima do lado de tras', '33354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 139, 1077, 'Marck Lopes Pearl', 1019, 'Do lado de cima do outro bloco', '34354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 142, 1084, 'Ao lado da loja Marizinha Mil Grau', 1017, 'Atras de cima', '35354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 142, 1084, 'Ao lado da loja do jao', 2145, 'Atras de tras', '36354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 151, 1084, 'Ao lado da loja Marizinha Do Dia 12', 9017, 'Em cima da sorveteria', '36354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 151, 1084, 'Em cima da loja da Marizinha Do Dia 12', 1313, 'Em cima da borracharia', '37354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 160, 1091, 'Em cima do prédio azul', 124017, 'Em cima da fábrica de ovo', '37354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 160, 1091, 'Em cima do carro azul', 114017, 'Em cima da fábrica de martelo', '38354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 163, 1091, 'Em cima do prédio azul', 774017, 'Em cima da fábrica de sapato', '39354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 163, 1091, 'Em cima do sobrado verde', 115017, 'Em cima da fábrica de colchao', '40354-142');

-- 
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 166,	1098,	'Rua firinfinfom',	589,	'Ao lado da rua 12', '28350-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 166,	1098,	'Rua firinfinfUNDO'	,5854, 'Em cima da casa 11','27354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 169,	1098,	'Rua FIrmanse'	,5814, 'Em cima da casa 13','26354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 169,	1098,	'Rua FIrmanse'	,5814, 'Em cima da classe 8 do bloco D','25354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 172,	1105,	'Rua Forward'	,1345, 'Em cima da classe 9 do bloco X','25354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 172,	1105,	'Em cima do prédio marrom'	,162345, 'Em cima do lado b','24354-142');


INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 175,	1105,	'Em cima do prédio amarelo', 162311, 'No bloco 4','20354-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 175,	1105,	'Rua luizin do trap azul', 162311, 'Em cima do lado b','19354-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 178,	1112,	'Rua lertrer trap azul', 12143, 'Em cima do lado c','19300-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 178,	1112,	'Rua jacinto favoretto', 110143, 'Studio veneza','18300-142');


INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 181,	1112,	'Rua joao ramalho favoretto', 14046, 'Sobrado preto','17300-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 181,	1112,	'Rua pedro estelita', 121126, 'Edificio D14 ','16300-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 184,	1119,	'Em cima da rua do ovo', 121113, 'Edificio Da Maca ','15300-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 184,	1119,	'Rua pedro fagundes', 100021, 'Edificio 13 de maio ','14300-142');

INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 187,	1119,	'Em cima da rua do limao', 111113, 'Edificio Da Maca ','13300-142');
INSERT INTO VEM_SER.ENDERECO ( ID_ENDERECO, ID_BAIRRO, ID_CIDADE, LOGRADOURO, NUMERO, COMPLEMENTO, CEP)
VALUES (VEM_SER.SEQ_ENDERECOS.nextval, 187,	1119,	'Rua do alvares abreu', 980021, 'Edificio 12 de ontem ','12300-142');

--SELECT VEM_SER.seq_pessoa.nextval FROM DUAL



SELECT  *  
FROM  VEM_SER.PAIS 
ORDER BY nome DESC

SELECT logradouro
FROM VEM_SER.ENDERECO
WHERE UPPER(logradouro) LIKE 'A%';


-- USAR O TRIM É MELHOR POIS ELE REMOVE OS ESPAÇOS EM BRANCO NO COMEÇO E NO FIM DA STRING.
SELECT cep
FROM VEM_SER.ENDERECO
WHERE TRIM(cep) LIKE '%0';


SELECT numero
FROM VEM_SER.ENDERECO
WHERE numero >= 1 AND numero =< 100 ;

-- Logradores que começam com rua
SELECT logradouro
FROM VEM_SER.ENDERECO
WHERE UPPER(logradouro) LIKE 'RUA%'
ORDER BY cep DESC;

-- Contagem geral
SELECT COUNT (*) 
	FROM VEM_SER.ENDERECO

-- Contagem cada cidade
SELECT ID_CIDADE, COUNT(LOGRADOURO)
FROM VEM_SER.ENDERECO
GROUP BY ID_CIDADE;



	