# URSA

Este projeto é um **Sistema Distribuído** para a "Universidade Regionalizada Salvador da América (URSA)". 

Este projeto permite o **envio/divulgação de oportunidades de emprego, estágio e bolsas** por parte de produtores rurais e empresas. 

Foram criadas diversas aplicações com interface de comunicação para divulgar as oportunidades:

- Receptor de oportunidades que se comunica meio de um **Socket UDP**, porta 2001;
- Servidor de acesso ao banco de dados que aceita conexões **TCP**, porta 1972;
- Web Services **SOAP**;
- Web Services **Rest**;
- Android **App**;
- Integração com **Twitter**.

## Banco de Dados

Usado para o armazenamento dos dados das oportunidades de emprego, estágio e bolsas disponibilizadas. 

O código gerencia as oportunidades da seguinte forma: 
- quando chegaram;
- quais foram atendidas;
- que tipos são os mais procurados.

A base de dados tem a seguinte disposição:

### Oportunidade
```
\#codigo: Integer (chave primária)
@codcargo: Integer (chave estrangeira cargo)
descricao: varchar(4000)
acesso: Integer (codificado)
ingresso: Timestamp
fechada: Timestamp
```

#### Input:

O campo ```acesso``` serve para indicar para alunos de quais cursos a oportunidade é válida. Na URSA são usados os seguintes acessos (alunos dos cursos):

1 – Somente Agronomia
2 – Somente Engenharia Agronomica
3 – Somente Agronogocio
4 – Agronomia ou Eng. Agronomica
5 – Agronomia ou Agronegocio
6 – Eng. Agronomica ou Agronegocio
7 – Todos os cursos

### Cargo
```
\#codcargo: Integer (chave primária)
descricao: varchar (100)
tipo: Integer
```

#### Input

O campo ```tipo``` serve para indicar o tipo da oportunidade para aquele cargo. Na URSA são usados os seguintes tipos:

1 – Emprego formal
2 – Estágio até 20h/semana
3 – Estágio acima de 20h/semana
4 – Estágio voluntário
5 – Bolsa de pesquisa
6 – Bolsa de extensão
7 – Bolsa de graduação

## Sockets UPD and TCP

O servidor/serviços suportam as seguintes operações:

- **Adiciona**: Adiciona uma oportunidade ao BD. Caso código da oportunidade já exista, não adicionar e informar um erro;
- **Altera**: - Altera uma oportunidade ao BD. Caso código da oportunidade não exista, não alterar e informar um erro;
- **Excluir**: apaga uma oportunidade do BD. Caso o código da oportunidade não exista no BD, retornar essa informação;
- **Consulta**: retorna os dados de uma oportunidade. Caso o código da oportunidade não exista no BD, retornar essa informação;
- **ListaOportunidades**: deve ser recebido o código do cargo e retornar uma lista contendo as oportunidades para aquele cargo;
- **ListaAbertas**: deve ser recebido o tipo de oportunidade (ou nenhum tipo, indicando que são todos) e retornar as oportunidades em aberto para aquele tipo;

Os servidores/serviços ao receberem uma solicitação de inclusão de oportunidade preenchem automaticamente o campo 'ingresso' com a data e hora de recebimento da solicitação no servidor/serviço. 

O servidor que se comunica com socket UDP suporta apenas mensagens de até 100 caracteres e não suporta as operações de ListaOportunidades e ListaAbertas. 

O servidor que se comunica por TCP possui uma interface com o usuário em modo texto, permitindo acesso aos funcionários da URSA para interagir com as oportunidades, a partir das operações previstas. Além disso, tanto ele quanto o servidor que se comunica com socket TCP constroi um log de processamento das operações realizadas.

## Web Services:

Os serviços Web disponibilizam o acesso as mesmas operações que o servidor TCP. No caso de serviços Rest, são implementadas operações em quatro métodos HTTP: GET, PUT, POST e DELETE. A codificação dos dados é XML em SOAP e Json em Rest.

O trabalho contêm com os seguintes clientes:

- **Cliente com interface de linha de comando para servidor UDP**: a aplicação recebe os argumentos em linha de comando, envia a mensagem de solicitação ao servidor, aguarda a resposta e mostra ao usuário. As opções disponíveis são as mesmas que o servidor disponibiliza;
- **Cliente com interface gráfica para interação com Serviço Web SOAP**: proporciona acesso a todas as operações disponibilizadas pelo Serviço SOAP;
- **Cliente Web para interação com o Serviço Web Rest**: a aplicação proporciona acesso a todas as operações disponibilizadas pelo serviço com uma interface Web. 

## URSA Story

A Universidade Regionalizada Salvador da América (URSA) é uma das maiores universidades de sua região. A região de abrangência da URSA compreende uma área essencialmente agrícola, com muitos produtores agropecuários de pequeno, grande e médio porte. Mesmo sendo agrícola, a região é muito bem servida de serviços de tecnologia da informação e comunicação, com grande conectividade e acesso indiscriminado por moradores e empresários a dispositivos computacionais de vários tipos. 

A URSA oferece diversos cursos de graduação aos moradores da região, dentre eles, os cursos de Agronomia, Engenharia Agronômica e Agronegócios (CST). Constantemente os produtores e as empresas da área agrícola entram em contato com os coordenadores da URSA para solicitar a indicação ou divulgar vagas de oportunidades: empregos, estágios ou bolsas (pesquisa, extensão ou desenvolvimento). As formas de chegada das oportunidades são tantas que os coordenadores e funcionários da URSA estão com problemas para gerenciar e divulgar essas oportunidades. 

#### This was an assignment for Distributed Systems class by Ph.D Marcelo Trindade Rebonatto at Passo Fundo University (2017-2).
