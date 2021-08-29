package aplicativos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Aeronave;
import classes.Piloto;


public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {

        final int MAX_ELEMENTOS = 20;
        int opcao = 5;
        int qtdCadastrados = 0;
        Scanner in = new Scanner(System.in);
        //

        /* 
            Talvez perceba que os Commits anteriores tinham uma Classe ArmazenarDados, fiz usando o conceito de alocação de memória 
            mas depois percebi que era desnecessário, e podia implementar tudo direto aqui, mas usei tudo dela como base
            para deixar os Vetores Dinâmicos, permitindo que seu tamanho seja alterado o tempo inteiro no programa
        */

        Piloto[] pilotosCadastrados; // Apenas declaração dos vetores, para ser possível reinstanciar a qualquer momento
        Aeronave[] aeronavesCadastradas;

        pilotosCadastrados = new Piloto[MAX_ELEMENTOS]; // Valor inicial dos Vetores: 20
        aeronavesCadastradas = new Aeronave[MAX_ELEMENTOS];
        

        //
        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            try {
                opcao = in.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Digite Somente Números!!");

            }
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == pilotosCadastrados.length) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }
                Piloto pilotoParaCadastrar = new Piloto();

                System.out.println("Nome do Piloto: ");
                pilotoParaCadastrar.setNome(in.nextLine());

                System.out.println("Matrícula do Piloto: ");
                pilotoParaCadastrar.setMatricula(in.nextLine());

                System.out.println("Brevê do Piloto: ");
                pilotoParaCadastrar.setBreve(in.nextLine());

                System.out.println("CPF do Piloto (000.000.000-00): ");
                
                boolean cadastrado = false; // Validar se o cpf esta corretamente digitado
                do {
                    try{
                        pilotoParaCadastrar.setCpf(in.nextLine());
                        cadastrado = true;
                    } catch(InputMismatchException ex){
                        System.out.println(ex.getMessage());
                        in.nextLine();
                        System.out.println("Digite Novamente: ");
        
                    }
                }while(cadastrado == false);

                Aeronave aeronaveParaCadastrar = new Aeronave();
                aeronaveParaCadastrar.setPiloto(pilotoParaCadastrar); // Adiciona a a pessoa em uma aeronave

                System.out.println("Categoria da Aeronave (Asa Fixa ou Asa Movel): ");
                System.out.println("1 - Asa Fixa  \n2 - Asa Móvel  ");
                String categoria = "UNKNOWN";
                int categoriaDigito = 0;
                boolean verificador = false;

                do {
                    try {
                        categoriaDigito = in.nextInt();
                        in.nextLine();
                        verificador = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Digite Somente Números!!");
                        
                    }
                } while (!verificador);

                if (categoriaDigito == 1){
                    categoria = "Asa Fixa";
                } else if (categoriaDigito == 2) {
                    categoria = "Asa Móvel";
                } else {
                    System.out.println("Categoria Desconhecida!! ");
                }
                aeronaveParaCadastrar.setCategoria(categoria);

                System.out.println("Modelo da Aeronave: ");
                aeronaveParaCadastrar.setModelo(in.nextLine());

                // Instancia e passa o piloto Novo e aeronave Nova para o sistema
                pilotosCadastrados[qtdCadastrados] = new Piloto(pilotoParaCadastrar); 
                aeronavesCadastradas[qtdCadastrados] = new Aeronave(aeronaveParaCadastrar);
                
                qtdCadastrados++;

                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);

            } else if (opcao == 2) {
                /**/ 
                if(qtdCadastrados == 0) {
                    System.out.println("Não Existe nenhum piloto cadastrado no momento!!");
                    continue;
                }

                
                for(int i = 0; i < qtdCadastrados; i++){
                    System.out.printf("\n-> Piloto número %d \nNome: %s \nMatrícula: %s \nBrevê: %s \nCPF: %s\n\n",
                        i + 1, pilotosCadastrados[i].getNome(),
                        pilotosCadastrados[i].getMatricula(),
                        pilotosCadastrados[i].getBreve(),
                        pilotosCadastrados[i].getCpf());

                    // Printa cada aeronave pertence ao mesmo piloto, caso seja o caso
                    Aeronave aeronaveDoPilotoAtual = new Aeronave();
                    aeronaveDoPilotoAtual = encontrarPilotoEmUmaAeronave(aeronavesCadastradas, pilotosCadastrados[i].getCpf());
                
                    System.out.println("Aeronave do piloto: ");
                    // Exibe as Aeronaves do Piloto atual
                    System.out.printf("> %s\n\n", aeronaveDoPilotoAtual.getModelo());

                        
                }
                /* */
                voltarMenu(in);
            } else if (opcao == 3) {

                if(qtdCadastrados == 0){
                    System.out.println("Não existe nenhum piloto cadastrado para ser buscado!!");
                    voltarMenu(in);
                    continue;
                } 

                String cpfSolicitado;
                boolean escritaCorreta = false;
                

                do {

                    System.out.println("\n\nCase queira sair dessa área, digite exit !!");
                    System.out.println("Digite o CPF para buscar o Piloto: ");
                    cpfSolicitado = in.nextLine();

                    if(cpfSolicitado.equals("exit")){ // Validação para sair do menu caso não saiba algum CPF
                        escritaCorreta = true;
                        continue;
                    }
                    try{
                        // Chama um método que recebe o Vetor e busca algum piloto com aquele CPF indicado
                        Piloto pilotoEncontrado = new Piloto(buscarPilotoCPF(cpfSolicitado, qtdCadastrados, pilotosCadastrados));
                        
                        // Pega o piloto encontrado e passa a um método que vai veriricar qual aeronave possui aquele 
                        // Piloto Cadastrado nela
                        Aeronave aeronaveEncontrada = new Aeronave(encontrarPilotoEmUmaAeronave(aeronavesCadastradas,
                         pilotoEncontrado.getCpf()));

                        System.out.println("\nResultado da Busca: ");
                        System.out.printf("Piloto %s \nMatricula: %s \nBrevê: %s \n== CPF: %s ==\nAeronave Modelo: %s",
                        pilotoEncontrado.getNome(), pilotoEncontrado.getMatricula(), pilotoEncontrado.getBreve(),pilotoEncontrado.getCpf(),
                        aeronaveEncontrada.getModelo());
                        escritaCorreta = true;
                        
    
                    } catch(InputMismatchException ex){
                        System.out.println(ex.getMessage());

                    } catch(NullPointerException ex) {
                        System.out.println(ex.getMessage());
                    }
                    


                } while(escritaCorreta == false);
                
                voltarMenu(in);
                continue;
                
            } else if (opcao == 4) {
            /* */ 
                System.out.print("Novo tamanho do armazenamento: ");
                int tamanhoNovo = 0;
                boolean cadastrado = false;
                do {
                    try {
                        tamanhoNovo = in.nextInt();
                        in.nextLine(); // Remove o entre preso no Buffer

                        if(tamanhoNovo <= pilotosCadastrados.length) {
                            System.out.printf("O tamanho deve ser pelo menos > %d!! ", pilotosCadastrados.length);
                            System.out.print("\nDigite Novamente: ");

                        } else {
                            cadastrado = true;
                        }

                        
                    } catch (InputMismatchException ex) {
                        System.out.println("Digite Somente Números!! ");
                        in.nextLine(); // Remove o entre preso no Buffer
                        
                    } 
                } while(!cadastrado);


                // Cria um vetor de Backup com os valores e tamanho do vetor atual
                Piloto[] pilotosBackup = new Piloto[pilotosCadastrados.length];
                Aeronave[] aeronavesBackup = new Aeronave[aeronavesCadastradas.length];

                // Atribui os respectivos valores para os vetores de Backup
                pilotosBackup = pilotosCadastrados;
                aeronavesBackup = aeronavesCadastradas;

                pilotosCadastrados = new Piloto[tamanhoNovo];
                aeronavesCadastradas = new Aeronave[tamanhoNovo];

                // Ciclo para restaurar pilotos
                for (int i = 0; i < qtdCadastrados; i++) {
                    pilotosCadastrados[i] = new Piloto();
                    pilotosCadastrados[i] = pilotosBackup[i];
                }

                // Ciclo para restaurar Aeronaves
                for (int i = 0; i < qtdCadastrados; i++) {
                    aeronavesCadastradas[i] = new Aeronave();
                    aeronavesCadastradas[i] = aeronavesBackup[i];
                }

                System.out.println("Tamanho Alterado com Sucesso!! ");
            /* */ 
            }
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }


    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }

    private static Piloto buscarPilotoCPF(String cpfSolicitado, int qtdCadastrados, Piloto... pilotos){


        for (int i = 0; i < qtdCadastrados; i++) {

            String teste = pilotos[i].getCpf(); // Pega um piloto no VETOR na posição atual

            if(teste.equals(cpfSolicitado)){ // Testa até encontrar um piloto
                return pilotos[i]; // Se o piloto for encontrado, retorna ele e sai da função
            }
        }
        // Se nada for encontrado, automaticamente retorna um erro
        throw new NullPointerException("Não foi encontrado Nenhum Piloto com o CPF: " + cpfSolicitado);

    }

    // Serve para encontrar a aeronave pertencente a algum piloto
    private static Aeronave encontrarPilotoEmUmaAeronave(Aeronave[] aeronaves, String cpf) {

        Aeronave aeronavesEncontradas = new Aeronave();

        // Valida se na posição atual existe uma aeronave com o cpf do piloto, se sim, atribui ao vetor
        for (int i = 0; i < aeronaves.length; i++) {
            if(aeronaves[i].getPiloto().getCpf().equals(cpf)){

                aeronavesEncontradas = aeronaves[i];
                break;
            }
            // Caso não seja true, apenas continua testando
        }

        return aeronavesEncontradas;
    }



}