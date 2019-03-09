### SD2019 Labs #1
#### Descoberta de Serviços

A descoberta dos serviços disponíveis numa rede local é um ingrediente
na concepção de sistemas distribuídos que se auto-configuram.

Na pilha TCP/IP, a descoberta de serviços pode ser conseguida por recurso a comunicação multiponto e a
a um endereço pré-acordado. 

Tipicamente, usa-se um endereço *IP MULTICAST* (e um porto), mais raramente, recorre-se a *brodcast*.

A descoberta pode operar em duas modalidades princípais: ***anúncios períodicos*** dos servidores ou por
***interrogação iniciada pelo cliente***. 

No primeiro caso, os servidores enviam periodicamente um datagrama para o canal multiponto,
contendo a informação de contacto.<br>O cliente, quando deseja descobrir um serviço, escuta o canal multiponto, no
endereço e porto pré-acordado, até receber a informação de contacto procurada, ou até achar que deve desistir. 

Na outra modalidade, os papeis invertem-se. Os servidores escutam o canal e respondem aos pedidos de contato iniciados pelos clientes.<br>
Tendo em conta que a comunicação não é fiável, os clientes estão obrigados a re-enviar o pedido de descoberta várias vezes até
obter resposta ou, na falta desta, até poderem concluir que que o serviço não está disponível.

#### Exercício

Pretende-se implementar a descoberta de serviços na rede local, na modalidade de anúncios períodicos.


Analise o código da classe `Discovery`, e complete a implementação do método `findUrisOf`, destinado
a ser usado por aplicações cliente. 

No pacote `http` apresenta-se um exemplo de utilização da classe `Discovery`, onde um cliente HTTP antes de realizar um pedido, <br>obtém o URI de contato do servidor HTTP, usando o método `findUrisOf` a completar.

Na sua implementação, tenha em conta os seguintes aspetos:

1. O formato dos anúncios - estes consistem em `<nome serviço>` `<TAB>` `<uri-do-servidor>`<br>
eg: `WebServer \t http://192.168.1.1:8000/` (sem espaços)<br><br>

2. É necessário definir um tempo máximo de espera até desistir.<br><br>

3. É necessário acumular o número de respostas desejado, enquanto não se esgotar o tempo de espera estipulado.<br><br>

4. O cliente não irá enviar mensagens, apenas receber (escutar anúncios).<br><br>

