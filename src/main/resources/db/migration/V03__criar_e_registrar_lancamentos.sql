-- Criação da tabela
CREATE TABLE lancamento (
    codigo BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor NUMERIC(10,2) NOT NULL,
    observacao VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    codigo_categoria BIGINT NOT NULL,
    codigo_pessoa BIGINT NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
);

-- Inserções de dados
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Salário mensal', '2017-06-10', NULL, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Bahamas', '2017-02-10', '2017-02-10', 100.32, NULL, 'DESPESA', 2, 2);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Top Club', '2017-06-10', NULL, 120, NULL, 'RECEITA', 3, 3);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('DMAE', '2017-06-10', NULL, 200.30, NULL, 'DESPESA', 3, 5);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Extra', '2017-03-10', '2017-03-10', 1010.32, NULL, 'RECEITA', 4, 6);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Bahamas', '2017-06-10', NULL, 500, NULL, 'RECEITA', 1, 7);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Top Club', '2017-03-10', '2017-03-10', 400.32, NULL, 'DESPESA', 4, 8);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Despachante', '2017-06-10', NULL, 123.64, 'Multas', 'DESPESA', 3, 9);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Pneus', '2017-04-10', '2017-04-10', 665.33, NULL, 'RECEITA', 5, 10);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Café', '2017-06-10', NULL, 8.32, NULL, 'DESPESA', 1, 5);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, NULL, 'DESPESA', 5, 4);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Instrumentos', '2017-06-10', NULL, 1040.32, NULL, 'DESPESA', 4, 3);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Café', '2017-04-10', '2017-04-10', 4.32, NULL, 'DESPESA', 4, 2);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa)
VALUES ('Lanche', '2017-06-10', NULL, 10.20, NULL, 'DESPESA', 4, 1);