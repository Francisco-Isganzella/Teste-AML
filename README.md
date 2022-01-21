# Teste-AML
Teste para participar do bootcamp AML 2022

Para executar o programa é necessário fazer download e instalação dos programas abaixo:
Java 11 - https://www.oracle.com/java/technologies/downloads/#java11-windows
IDE NetBeans 12 ou superior - https://netbeans.apache.org/download/index.html
BD MySQL - https://dev.mysql.com/downloads/installer/

É nessessário alterar no código fonte a String "senhaDoBancoDeDadosMySql" na classe "FabricaConexao", para a senha do seu banco de dados MySQL instalado.
e também alterar a String "path" na classe "Arquivo", para o caminho onde foi clonado o projeto.

Questões
A – Com suas palavras explique o que é lavagem de dinheiro.

    R: Lavagem de dinheiro é quando alguém tenta fazer parecer lícito o dinheiro que obteve de forma ilícita ,
    circulando esse dinheiro em empresas que aparentam ser reais, mas que quase sempre são criadas apenas para este fim.

B – O que é Cartão de Pagamento do Governo Federal (CPGF), e qual a sua finalidade.

    R: É um cartão semelhante a um cartão de crédito, ele é utilizado pelo governo para pagamento de despesas próprias.

C – Quem pode utilizar o CPGF?

    R: Segundo o decreto Nº 5.355 de 25 de Janeiro de 2005 art 1º, pelos órgãos e entidades da administração pública federal
    integrantes do orçamento fiscal e da seguridade social.

D – Qual a URL onde é possível fazer o download dos arquivos do CPGF?

    R: https://www.portaltransparencia.gov.br/download-de-dados/cpgf

E – Qual a URL da paǵina com a descrição dos campos (ou dicionário de dados) da CPGF?

    R: https://www.portaldatransparencia.gov.br/pagina-interna/603393-dicionario-de-dados-cpgf

F – É possível identificar o nome e o documento do portador do CPGF, em todas as
movimentações ou há movimentações onde não é possível identificar o portador?

    R: Não, existem muitas movimentações onde a informação do nome e documento do portador é omitida.

G – É possível identificar o Órgão do portador do CPGF?

    R: Sim, em todos os casos a identificação do Órgão do portador do cartão foi informada.
    
H - Qual o nome do Órgão cujo código é 20402?

    R: Agência Espacial Brasileira

I - É possível identificar o Nome e Documento (CNPJ) dos favorecidos pela utilização do
CPGF?

    R: Existem algumas movimentações onde existe essa informação, porém em muitas outras elas são omitidas.

J – É possível identificar a data e o valor das movimentações financeiras do CPGF, em
todas as movimentações? Ou há movimentações onde não é possível identificar as datas e
ou valores?

    R: Em alguns casos existe a informação de data e valor, porém existem muitas transações sem a informação
    da data e também nas transações onde as datas não estavam sendo informadas o valor estava editado como 1000,
    o que faz parecer que é uma informação errada, tendo em vista a repetição desse mesmo valor em varias movimentações.


QUESTÕES DE CÓDIGO


K (código) – Qual a soma total das movimentações utilizando o CPGF?

    R: R$ 5.619.007,95
    
![valorTotal](https://user-images.githubusercontent.com/76000194/150594203-d9dd53bf-2337-4b6a-a262-5f3b46e2e1e2.jpg)

L (código) – Qual a soma das movimentações sigilosas ?

    R: R$ 3.108.731,15
    
![valorTotalSigilosas](https://user-images.githubusercontent.com/76000194/150594233-2d39af98-ce50-4084-94d8-8c232b891c6b.jpg)

M (código) – Qual o Órgão que mais realizou movimentações sigilosas no período e qual o
valor (somado)?

    R: Departamento de Policia Federal
    
![valorTotalSigilosasOrgao](https://user-images.githubusercontent.com/76000194/150594370-0dd51e95-640b-401f-bd2a-289576d8774e.jpg)

N (código) – Qual o nome do portador que mais realizou saques no período? Qual a soma
dos saques realizada por ele? Qual o nome do Órgão desse portador?

    R: Rafael Ferreira realizou 25 saques, órgão Instituto Chico Mendes
    
![valorTotalSaquesPortador](https://user-images.githubusercontent.com/76000194/150594673-250c57a6-64c6-4c6c-9d3a-4266d8f80f5f.jpg)

O (código) – Qual o nome do favorecido que mais recebeu compras realizadas utilizando o
CPGF?

    R: MercadoPago, 123 compras.
    
![valorTotalFavorecidos](https://user-images.githubusercontent.com/76000194/150595751-454a6d20-4a16-431c-81db-b99270e74782.jpg)

P - Descreva qual a abordagem utilizada para desenvolver o código para os ítens de K a O.

    R: Utilizei a linguagem de programação Java para desenvolver um programa Desktop que automatiza a leitura do arquivo em formato csv que contém
    as informações de uso do cartão CPGF e também automatiza as respostas para as perguntas de K a O por meio de uma interface gráfica de facil utilização.
    Utilizei o método de conexão JDBC com o banco de dados MySQL, para assim poder guardar os dados obtidos através do arquivo e depois poder consultar
    e apresentar na interface os dados já filtrados.

