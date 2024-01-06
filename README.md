# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="400" src="https://user-images.githubusercontent.com/101413385/173164615-192ca98a-1a44-480e-9229-9f82f456eec8.png">
</p>

---

## 🖥️ Tecnologias Utilizadas

- Java
- Java Swing
- JDBC
- MySQL
- Biblioteca JCalendar
- IntelliJ (IDE)

## 💬 Descrição

O projeto "Hotel Alura" representa um desafio de Back-end desenvolvido como parte do programa ONE - Oracle Next Education, em colaboração com a Alura. Implementado em Java, utiliza JDBC para interação com um banco de dados e Java Swing para a criação da interface gráfica. Este sistema de reservas de hotel fictício, permite efetuar o cadastro de reservas e seus hóspedes, realizar buscas, exclusões e edições de informações relacionadas a hóspedes e reservas, proporcionando uma solução completa para a gestão eficiente dessas operações.

## 👨🏻‍💻 Executando o Programa

Para executar este programa, siga os passos abaixo:

### 1. Pré-requisitos
- Primeiramente, é necessário realizar o clone do repositório Git para obter todos os arquivos necessários:
    ```bash
    git clone https://github.com/j0aoarthur/AluraHotel-ONE
    ```
- Certifique-se de possuir os JARs necessários para a execução do programa. Você pode baixá-los [aqui](https://uploadnow.io/f/H7DM3rp). Após o download, certifique-se de adicioná-los na pasta de bibliotecas externas do seu IDE preferido.
- Tenha o Java instalado em sua máquina. Você pode fazer o download e instalação [aqui](https://www.oracle.com/java/technologies/javase-downloads.html).

### 2. Configuração do Banco de Dados

1. Abra o arquivo Connection.properties localizado no diretório do projeto.
2. Configure as informações da sua base de dados de acordo com o tipo utilizado. No exemplo, utilizamos o MySQL. Se estiver utilizando outro banco de dados, ajuste as configurações conforme necessário.

- Exemplo para MySQL

    ```bash
    # Configurações do Banco de Dados MySQL
    dataBase.url=jdbc:mysql://localhost:3306/nome_do_banco
    dataBase.user=seu_usuario
    dataBase.password=sua_senha
    ```

### 3. Compile e Execute o Arquivo TesteViews.java

Após seguir os passos anteriores, navegue até a pasta "src" do projeto e compile o arquivo "TesteViews.java". Você pode usar o seguinte comando:

```bash
javac TesteViews.java
```

Após a compilação, execute o programa com o comando:

```bash
java TesteViews
```

<br>
Agora, o programa deve ser executado com sucesso. Certifique-se de ter seguido todos os passos corretamente para evitar problemas durante a execução.

## Base de Dados

Este projeto utiliza o sistema de gerenciamento de banco de dados MySQL. A estrutura da base de dados pode ser compreendida por meio do diagrama a seguir:

<p align="center">
     <img width="800" alt="Imagem de diagrama da Base de dados MySQL utilizada no projeto" src="https://github.com/j0aoarthur/AluraHotel-ONE/assets/121466923/ad2417af-a70c-44c5-952b-e25dcb01c1f8">
</p>

## Sobre o Autor

Eu sou João Arthur Britto, o desenvolvedor por trás da parte lógica e a conexão do banco de dados do Hotel Alura. Com uma paixão por programação, criei esta aplicação como forma de aprendizado da linguagem Java e sua conexão com banco de dados com o JDBC e conclusão de uma das etapas da formação de Back-end com Java proposta pelo programa ONE - Oracle Next Education.

Se você tiver alguma dúvida, sugestão ou comentário, sinta-se à vontade para entrar em contato comigo. Você pode me encontrar em <b>joaoabritto.dev@gmail.com</b>, ou visitar meu perfil no [GitHub](https://github.com/j0aoarthur) e [LinkedIn](https://www.linkedin.com/in/joao-arthur-britto).

## 🎖️ Badge recebida pela conclusão do projeto

<p align="center">
     <img width="600" alt="Badge recebida pela Alura e a Oracle pela conclusão do projeto" src="https://github.com/j0aoarthur/AluraHotel-ONE/assets/121466923/80e71f0a-987b-4806-9c4e-67e2e4f71c30">
</p>


