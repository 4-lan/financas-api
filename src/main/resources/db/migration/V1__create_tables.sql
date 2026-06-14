CREATE TABLE categorias (
    id_categoria UUID PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE transacoes (
    id_transacao UUID PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL,
    valor NUMERIC(12, 2) NOT NULL,
    data DATE NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    id_categoria UUID,
    data_criacao TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_transacao_categoria
                        FOREIGN KEY(id_categoria)
                        REFERENCES categorias(id_categoria)
);