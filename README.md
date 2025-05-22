# 💬 ChatWebSocket

**ChatWebSocket** é um projeto de chat em tempo real desenvolvido como exercício de aprendizado. Utiliza **WebSocket** com **Java EE**, **JSF (PrimeFaces)**, **JPA (Hibernate)** e **PostgreSQL**, com deploy no **WildFly**. Ele demonstra de forma prática como integrar essas tecnologias modernas em uma aplicação web interativa e funcional.

---

## 🚀 Tecnologias Utilizadas

- Java EE (Jakarta EE)
- JSF com PrimeFaces
- WebSocket (javax.websocket)
- JPA com Hibernate
- PostgreSQL
- Servidor de aplicação: WildFly
- Maven

---

## ⚙️ Funcionalidades

- Login simples por nome
- Bate-papo em tempo real via WebSocket
- Diferenciação visual entre mensagens do próprio usuário e dos outros
- Armazenamento das mensagens no banco de dados (PostgreSQL)
- Design responsivo com PrimeFaces

---

## 🔧 Como Executar

1. **Clone o repositório**  
   ```bash
   git clone https://github.com/seu-usuario/ChatWebSocket.git
   cd ChatWebSocket

   Configure o WildFly

2. Crie um DataSource com o JNDI java:/CHAT_DB

   Aponte para seu banco PostgreSQL com o nome correto

3.Configure o persistence.xml

   Certifique-se de que o non-jta-data-source esteja com java:/CHAT_DB

4.Crie o banco e a tabela

   O Hibernate criará automaticamente as tabelas se hibernate.hbm2ddl.auto estiver como update ou create

5.Empacote e faça o deploy
   bash
      mvn clean package
   Suba o .war no WildFly

6.Acesse via navegador

arduino
http://localhost:8080/ChatWebSocket

📌 Melhorias Futuras
Autenticação com usuários reais

Interface mais moderna com feedback visual

Listagem de mensagens anteriores ao entrar no chat

Suporte a múltiplas salas de conversa

🧑‍💻 Autor
Projeto criado por [Samuel Ferreira] como prática de desenvolvimento com WebSocket e integração com banco de dados.

