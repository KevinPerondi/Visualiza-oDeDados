CREATE TABLE Municipio (
	Nome varchar(50),
	Cod_Municipio int,
	Estado varchar(50),
    CONSTRAINT PK_Municipio PRIMARY KEY (Cod_Municipio)
);

CREATE TABLE Defeso (
    Portaria varchar(100) NOT NULL,
    Data_Inicio DATE,
    Data_Termino DATE,
    CONSTRAINT PK_Defeso PRIMARY KEY (Portaria)
    
);

CREATE TABLE Pescador (
    CPF_pescador varchar(15) NOT NULL,
    RGP varchar(15),
    PIS float,
    Numero_de_Requerimento int,
    Data_de_Requerimento DATE,
    Nome varchar(100),
	Cod_Municipio int,
    Portaria_Defeso varchar(100),
    CONSTRAINT PK_pescador PRIMARY KEY (CPF_pescador),
    FOREIGN KEY (Cod_Municipio) REFERENCES Municipio(Cod_Municipio),
    FOREIGN KEY (Portaria_Defeso) REFERENCES Defeso(Portaria)
);

CREATE TABLE Parcela (
	Id SERIAL NOT NULL ,
    Data_Emissao DATE,
    Num_Parcela INT,
    Data_de_Saque DATE,
    Valor_da_Parcela float,
    Data_de_Restituicao DATE,
    Valor_da_Restituicao float,
    Cod_Situacao_Parcela int,
    Descricao_Situacao_Parcela varchar(50),
    CPF_pescador varchar(15) references Pescador(CPF_pescador),
    CONSTRAINT PK_Parcela PRIMARY KEY (ID)
);
