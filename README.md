![Odontoclinica](https://github.com/ezioweb/Integrador-backend/blob/5d35cc34bf7878987816f46715d7231f3def71da/odonto-grupo1/src/main/resources/image/Header-logo.png) 

*Seu sistema completo para consultório odontológico*
# Projeto Integrador Backend 
Alunos: Ezio Lorenzetti, Daniel Marques, Eduardo Sakamoto, Lucas Mota Barbosa da Silva, Guilherme Mello

Esse projeto foi feito com Sprints pelo [Miro](https://miro.com/welcomeonboard/TEtSUEltOTB3UFBCWUhPMlNpQ0phRzRhVVNsbnF6MFkxZDdSUWE1emtSMjRMNVlqeDNOalNWTWZPQklwNzhPeHwzNDU4NzY0NTMyNDQyMTYwMTI1?share_link_id=762147440153)

## Objetivos do projeto 
- Administração de dados odontológicos
- Administração de pacientes
- Login
- Registrar uma consulta 

## Ferramentas

- Spring Boot 
- Postman 
- Dependencias 
  - H2
  - Log4J
  - Lombok
  - Junit Jupter

## Links externos:
- UML do projeto [Draw.io](https://drive.google.com/file/d/1T8AZxg4sDGHIxpWgD_bPGuN7IGOnxsEE/view?usp=sharing)
- Repositório Forntend -  não integrado [Github](https://github.com/ezioweb/Integrador-frontend)
- Layout [Layout no FIGMA](https://www.figma.com/file/NWpetzO9OMo3Nwc1IJ9a7E/Odonto-Cl%C3%ADnica?node-id=1%3A2)

  
  ## Requests:
  
- Dentistas 
    - .../dentista
      - @Get BuscarTodosDentistas 
      - @Post salvarDentista
        - Nome / Sobrenome / Matrícula
      - @Get Buscar Dentistas por ID
        - .../dentista/buscaid
          - Value: ID
      - @Patch AlterarMatricula
        - Preencher todos os valores: Nome / Sobrenome / Matrícula
      - @Delete Deletar dentista por ID
          
 - Pacientes
    - .../paciente
      - @Get buscarTodosPacientes
      - @Post salvarPaciente
        - Nome / Sobrenome / RG / (Endereço - Rua / Número / Bairro)
      - @Patch AlterarDados
        - Preencher todos os valores: ID / Nome / Sobrenome / RG
      - @Delete excluirPaciente por ID
        
 - Endereço
    - .../endereco
      - @Get BuscarTodosEnderecos
      - @Post salvarEndereço
        - Value: Rua / Número / Bairro
      - @Patch Alterar endereço 
        - Preencher todos os valores: ID / Rua / Número / Bairro
      - @Delete Deletar endereço por Id
        
 - Consultas
    - .../consulta
      - @Get BuscarTodasConsultas
      - @Post SalvarConsulta
        - Value: Rua / Número / Bairro
      - @Patch AlterarConsulta 
        - Preencher todos os valores: ID / Dentista / Paciente / dataHoraConsulta
      - @Delete ExcluirConsulta por Id
 
 
