package aplicativos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Aeronave;
import classes.ArmazenarDados;
import classes.Pessoa;


public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {

        final int MAX_ELEMENTOS = 20;
        int opcao = 5; 
        int qtdCadastrados = 0;
        Scanner in = new Scanner(System.in);
        //

        /* 
            Esta classe substitui o vetor original q veio com o programa para evitar um código muito grande na hora de 
            Aumentar o espaço original do vetor, pois agora tudo é salva num vetor dentro de uma instância q no inicio do programa
            este vetor é instanciado dentro da classe ArmazenarDados e passa a ter um local de memória para guardar os dados
            dos pilotos, caso seja expandido, se instancia novamente este vetor e passa um backup do dados para ele
            Nesta versão final fica mais simples e evitar fazer tratamento para lidar com dois vetores diferentes o tempo todo, 
            pois agora só se tem um e tudo está dinamizado para ele

            :3

        */
        ArmazenarDados vetorPilotos = new ArmazenarDados();
        vetorPilotos.iniciarVetor(MAX_ELEMENTOS);
        

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
                if (qtdCadastrados == vetorPilotos.getPilotos().length) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }
                Pessoa pilotoParaCadastrar = new Pessoa();

                System.out.println("Nome do Piloto: ");
                pilotoParaCadastrar.setNome(in.nextLine());

                System.out.println("Matrícula do Piloto: ");
                pilotoParaCadastrar.setMatricula(in.nextLine());

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

                Aeronave aeronave = new Aeronave();
                aeronave.setPiloto(pilotoParaCadastrar); // Adiciona a a pessoa em uma aeronave

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
                aeronave.setCategoria(categoria);

                System.out.println("Modelo da Aeronave: ");
                aeronave.setModelo(in.nextLine());

                vetorPilotos.setPilotos(pilotoParaCadastrar, qtdCadastrados);
                vetorPilotos.setAeronaves(aeronave, qtdCadastrados);
                
                qtdCadastrados++;

                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);

            } else if (opcao == 2) {
                /**/ 

                
                for(int i = 0; i < qtdCadastrados; i++){
                    System.out.printf("\n-> Piloto número %d \nNome: %s \nMatrícula: %s \nCPF: %s\n\n",
                        i + 1, vetorPilotos.getPilotos()[i].getNome(), 
                        vetorPilotos.getPilotos()[i].getMatricula(), 
                        vetorPilotos.getPilotos()[i].getCpf());

                    // Printa cada aeronave pertence ao mesmo piloto, caso seja o caso
                    Aeronave aeronavesDoPilotoAtual = new Aeronave();
                    aeronavesDoPilotoAtual = encontrarPilotoEmUmaAeronave(vetorPilotos.getAeronaves(), vetorPilotos.getPilotos()[i].getCpf());
                
                    System.out.println("Aeronave do piloto: ");
                    // Exibe as Aeronaves do Piloto atual
                    System.out.printf("> %s\n\n", aeronavesDoPilotoAtual.getModelo());

                        
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

                    System.out.println("\n\nCase queira sair dessa área, digite null !!");
                    System.out.println("Digite o CPF para buscar o Piloto: ");
                    cpfSolicitado = in.nextLine();

                    if(cpfSolicitado.equals("null")){ // Validação para sair do menu caso não saiba algum CPF
                        escritaCorreta = true;
                        continue;
                    }
                    try{
                        Pessoa pilotoEncontrado = new Pessoa(buscarPilotoCPF(cpfSolicitado, qtdCadastrados, vetorPilotos.getPilotos()));
                        Aeronave aeronaveEncontrada = new Aeronave(encontrarPilotoEmUmaAeronave(vetorPilotos.getAeronaves(),
                         pilotoEncontrado.getCpf()));

                        System.out.println("\nResultado da Busca: ");
                        System.out.printf("Piloto %s \nMatricula: %s \n== CPF: %s ==\nAeronave Modelo: %s",
                        pilotoEncontrado.getNome(), pilotoEncontrado.getMatricula(), pilotoEncontrado.getCpf(),
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

                        if(tamanhoNovo <= 20) {
                            System.out.println("O tamanho deve ser pelo menos > 20!! ");
                            System.out.print("\nDigite Novamente: ");

                        } else {
                            cadastrado = true;
                        }

                        
                    } catch (InputMismatchException ex) {
                        System.out.println("Digite Somente Números!! ");
                        in.nextLine(); // Remove o entre preso no Buffer
                        
                    } 
                } while(!cadastrado);


                vetorPilotos.AumentarArmazenamento(vetorPilotos.getPilotos(), tamanhoNovo, qtdCadastrados, vetorPilotos.getAeronaves());

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

    private static Pessoa buscarPilotoCPF(String cpfSolicitado, int qtdCadastrados, Pessoa... pilotos){


        for (int i = 0; i < qtdCadastrados; i++) {
            String teste = pilotos[i].getCpf();
            if(teste.equals(cpfSolicitado)){ // Testa até encontrar um piloto
                return pilotos[i];
            }
        }
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