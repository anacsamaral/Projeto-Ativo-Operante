# Sistema de Gestão de Denúncias e Feedbacks

Este projeto consiste num sistema completo para o registo de denúncias por parte dos cidadãos e a gestão/moderação das mesmas por administradores de órgãos competentes.

---

## 🛠️ Tecnologias Previstas
* **Backend:** Java (Spring Boot, Spring Data JPA, Spring Security)
* **Banco de Dados:** Oracle SQL / H2 Database
* **Autenticação:** JSON Web Token (JWT)
* **Frontend:** HTML5, CSS3, JavaScript (ou Framework a definir)

---

## 🚀 Roadmap de Desenvolvimento

O projeto foi dividido em 5 fases principais de desenvolvimento:

### Fase 1: Persistência de Dados
- [x] Definição e criação da base de dados.
- [x] Mapeamento das entidades.
- [ ] Criação dos repositórios JPA.

### Fase 2: Backend & API Rest (Filtros e Segurança)
As APIs estão divididas por perfis de acesso:

#### 🔐 Acesso Público
- [ ] Login com retorno de token.

#### 👤 Perfil Cidadão
- [ ] Cadastro de novo utilizador (Cidadão).
- [ ] Obter lista das suas próprias denúncias.
- [ ] Listar órgãos competentes.
- [ ] Listar tipos de problemas.
- [ ] Visualizar feedbacks recebidos nas suas denúncias.

#### ⚖️ Perfil Administrador
- [ ] CRUD completo de Tipos de Problema.
- [ ] CRUD completo de Órgãos Competentes.
- [ ] Listagem geral de todas as denúncias.
- [ ] Exclusão de denúncias.
- [ ] Registo de feedback oficial em denúncias.

### Fase 3: Portal Público (Frontend)
- [ ] Home page principal.
- [ ] Interface de Login.
- [ ] Formulário de cadastro de novo "Cidadão".

### Fase 4: Painel Administrativo (Frontend)
- [ ] Área restrita para administradores.
- [ ] Interfaces para gestão (CRUDs) de órgãos e tipos de problemas.
- [ ] Módulo de moderação (Visualizar, apagar e responder denúncias).

### Fase 5: Área do Cidadão (Frontend)
- [ ] Interface para preenchimento e envio de novas denúncias.
- [ ] Dashboard de acompanhamento: visualização de denúncias enviadas e respetivos feedbacks.

---

## 🔐 Segurança
O sistema utiliza **Spring Security** com autenticação via **Token JWT**.
- O acesso às funcionalidades de Cidadão e Administrador requer um token válido.
- Apenas a rota de login é acessível sem autenticação prévia.

---

## 📝 Licença
Este projeto é para fins académicos e de desenvolvimento profissional.