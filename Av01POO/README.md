# Enunciado

Ampliar as funcionalidades do programa de modo que:



- Cadastrar Piloto 
- Listar pilotos cadastrados
- Localizar piloto pelo CPF. 
- Expandir o tamanho do vetor para que sua capacidade de armazenamento seja ampliada (não pode haver perda de informações). Para isso, solicite ao usuário o novo tamanho de armazenamento, crie um novo vetor desse tamanho, passe os itens de um vetor para o outro e realize todas as demais operações necessárias para concluir o processo e garantir que o programa continuará funcionando sem mais alterações.



# Problemas / Soluções

- Cadastrar um piloto
  * Recolher dados da pessoa / piloto (nome, matrícula, cpf) a partir do usuário
- Retornar o registro de todos os pilotos disponíveis
  * Verificar se o vetor não está vazio
- Retornar um piloto pelo seu cpf
  * Pegar do usuário um cpf e buscar em cada índice do vetor se existe algum piloto com aquele cpf
- Expandir o vetor
  * Criar um novo vetor e pedir ao usuário o tamanho, pegar este tamanho e instanciar o vetor
