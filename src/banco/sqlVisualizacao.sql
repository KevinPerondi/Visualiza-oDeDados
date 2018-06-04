--CREATE DATABASE baseVisualizacao;
DROP TABLE IF EXISTS Parcela;
DROP TABLE IF EXISTS Pescador;
DROP TABLE IF EXISTS Municipio;
DROP TABLE IF EXISTS Defeso;

CREATE TABLE Municipio (
    Nome_Mun varchar(50),
    Cod_Municipio int,
    Estado varchar(5),
    CONSTRAINT PK_Municipio PRIMARY KEY (Cod_Municipio)
);

CREATE TABLE Defeso (
    Portaria varchar(100) NOT NULL,
    Data_Inicio varchar(10),
    Data_Termino varchar(10),
    CONSTRAINT PK_Defeso PRIMARY KEY (Portaria)
    
);

CREATE TABLE Pescador (
    CPF_pescador int NOT NULL,
    RGP float,
    PIS float,
    Numero_de_Requerimento int,
    Data_de_Requerimento varchar(10),
    Nome varchar(100),
    Cod_Municipio int,
    Portaria_Defeso varchar(100),
    CONSTRAINT PK_pescador PRIMARY KEY (CPF_pescador),
    FOREIGN KEY (Cod_Municipio) REFERENCES Municipio(Cod_Municipio),
    FOREIGN KEY (Portaria_Defeso) REFERENCES Defeso(Portaria)
);

CREATE TABLE Parcela (
    Id SERIAL NOT NULL ,
    Data_Emissao varchar(10),
    Num_Parcela INT,
    Data_de_Saque varchar(10),
    Valor_da_Parcela float,
    Data_de_Restituicao varchar(10),
    Valor_da_Restituicao float,
    Cod_Situacao_Parcela int,
    Descricao_Situacao_Parcela varchar(25),
    CPF_pescador int references Pescador(CPF_pescador),
    CONSTRAINT PK_Parcela PRIMARY KEY (ID)
);
--quantidade de pescadores por estado
--select COUNT(*),Estado from Pescador P,Municipio M where P.cod_municipio = M.Cod_Municipio group by M.Estado

--select COUNT(*) from pescador;
--select COUNT(*) from parcela;
--select * from parcela limit 5;
--select SUM(valor_da_parcela:: DOUBLE PRECISION) from parcela limit 2;

/*select COUNT(*),split_part(data_de_saque::text,'/',2) as mes,
	split_part(data_de_saque::text,'/',3) as ano
	from parcela
	group by 2,3
	order by 2,3;*/

/*select data_de_saque,COUNT(*) from parcela
group by data_de_saque
order by data_de_saque;*/

/*select data_de_saque,SUM(valor_da_parcela)::float from parcela
group by data_de_saque
order by data_de_saque;*/

/*
selecionando todas as parcelas por mes

select substr(data_de_saque::text,4,10) as dataE,valor_da_parcela
from parcela
where cod_situacao_parcela = 2 and data_de_saque like '%2010'
order by dataE;
*/