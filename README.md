## Setup do ambiente de trabalho

#### Software:

Pré-requisitos/software recomendado:
- IDE: Eclipse **2018‑12**
- Java: JDK 8

Podem utilizar qualquer IDE com que se sintam à vontade, no entando os docentes da cadeira vão dar suporte apenas ao acima referido.

#### Como importar o código base:

O código base das aulas práticas está disponível em https://github.com/smduarte/sd2019-labs

Existem várias formas de importar este código (e eventualmente importar o código base dos projectos) para um workspace do Eclipse:

##### Alternativa 1 - Importar directamente do Git no Eclipse (forma mais simples):

- Abrir o Eclipse 2018‑12, utilizando como workspace uma pasta à escolha.
- Carregar em *File* -> *Import...*
- Escolher *Git* -> *Projects from Git*. Carregar em *Next >*
- Escolher *Clone URI*. Carregar em *Next >*
- No campo *Location -> URI*, colar o URI do repositório Git (https://github.com/smduarte/sd2019-labs). 
Os outros campos serão preenchidos automaticamente (não é necessário autenticação). Carregar em *Next >*
- Neste menu não é necessário alterar nada, apenas confirmar que o item *master* está selecionado. Carregar em *Next >*
- O campo *Destination -> Directory* pode ser alterado para descarregar o repositório para uma pasta diferente. Carregar em *Next >*
- Neste menu, o código do repositório Git começa automaticamente a ser descarregado para a pasta escolhida no passo anterior. 
Quando este processo terminar não é necessário alterar nenhuma opção. Carregar em *Next >*
- Por fim, deverá aparecer uma lista dos projectos detectados no repositório que foi descarregado (SD2019-Labs-P1, SD2019-Labs-P2, ...).
Confirmar que os projectos estão selecionados e carregar em Finish.

##### Alternativa 2 - Obter código e importar manualmente:

- Obter o código directamente do repositório. Para isto existem duas formas:
    1- Descarregar o Zip do código manualmente: Abrir a página do repositório num browser e carregar em *Clone or Download -> Download Zip*. De seguida extrair para uma pasta à escolha.
    2 - Utilizando o Git, executar o comando: `git clone https://github.com/smduarte/sd2019-labs`
- Abrir o Eclipse num workspace à escolha
- Carregar em *File* -> *Import...*
- Escolher a opção *General* -> *Existing Projects into Workspace*. Carregar em *Next >*
- Em *Select root directory*, escolher a pasta para onde o repositório foi descarregado.
- No campo *Projects:* deverá aparecer a lista dos projectos encontrados. Confirmar que estão selecionados e carregar em *Finish*
