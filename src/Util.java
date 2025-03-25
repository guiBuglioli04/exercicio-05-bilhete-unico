import java.text.DecimalFormat;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {
    private final Bilhete[] bilhete = new Bilhete[2];
    private int index = 0;

    public void menuPrincipal(){
        int opcao;
        String menu = "1. Administrador\n2. Usuário\n3. Finalizar";
        do {
            opcao = parseInt(showInputDialog(menu));
            if( opcao<1 || opcao > 3){
                showMessageDialog(null,"Opção invalida");
            }else {
                switch (opcao){
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        menuUsuario();
                        break;
                }//switch

            }//else
        }while(opcao!= 3);

    }

    private void menuAdministrador(){
        int opcao;
        String menu = "1. Emitir bilhete\n2. Listar bilhetes cadastrados\n3. Remover bilhete\n4. sair";

        do {
            opcao = parseInt(showInputDialog(menu));
            if( opcao<1 || opcao > 4){
                showMessageDialog(null,"Opção invalida");
            }else {
                switch (opcao){
                    case 1:
                        emitirBilhete();
                        break;
                    case 2:
                        listarBilhetes();
                        break;
                }//switch

            }//else
        }while(opcao!= 4);
    }

    private void menuUsuario(){
        int opcao;
        String menu="1. Carregar bilhete\n2. Consultar saldo\n3. Passar na catraca\n4. Sair";

        do {
            opcao = parseInt(showInputDialog(menu));
            if(opcao < 1 || opcao > 4){
                showMessageDialog(null,"Opção inválida");
            }else {
                switch (opcao){
                    case 1:
                        carregarBilhete();
                        break;
                    case 2:
                        consultarSaldo();
                        break;
                    case 3:
                        passarNaCatraca();
                        break;
                }
            }
        }while (opcao!=4);

    }



    private void emitirBilhete(){
       long cpf;
       String nome, perfil;

        if(index< bilhete.length){
            nome = showInputDialog("Nome do usuário: ");
            cpf = parseLong(showInputDialog("CPF: "));
            perfil = showInputDialog("Perfil --> professor, estudante ou comum");
            bilhete[index] = new Bilhete(new Usuario(nome, cpf, perfil));
            index++;
        }else {
            showMessageDialog(null,"Entre em contato com a adm");
        }

    }

    private void listarBilhetes(){
        String aux = "";
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < index; i++) {
            aux += "Número do bilhete: " + bilhete[i].numero+"\n";
            aux += "Nome do usuário: " + bilhete[i].usuario.nome+"\n";
            aux += "Saldo: " + df.format(bilhete[i].saldo) + "\n";
            aux += "Perfil: " + bilhete[i].usuario.perfil+"\n";
            aux+= "------------------------------------------------------\n";
        }
        showMessageDialog(null,aux);
    }

    //USUARIO -------------------

    private void carregarBilhete(){
        int posicao = pesquisar();
        double valor;

        if(posicao != -1){
            valor = parseDouble(showInputDialog("valor da recarga: "));
            bilhete[posicao].carregar(valor);
        }
    }


    private void consultarSaldo(){
        int posicao = pesquisar();
        if(posicao != -1){
            showMessageDialog(null,"Saldo R$"+bilhete[posicao].consultarSaldo());
        }
    }


    private void passarNaCatraca(){
        int posicao = pesquisar();
        if(posicao!=-1){
            bilhete[posicao].passarNaCatraca();
        }
    }

    private int pesquisar(){
        int posicao=-1;
        long cpf = parseLong(showInputDialog("CPF"));

        for (int i = 0; i < index; i++) {
            if(bilhete[i].usuario.cpf == cpf){
                posicao = i;
                return posicao;
            }
        }
        showMessageDialog(null,"CPF não encontrado");
        return posicao;
    }

}
