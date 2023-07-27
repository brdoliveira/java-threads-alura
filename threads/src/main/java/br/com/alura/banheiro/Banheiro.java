package br.com.alura.banheiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {

    private boolean ehSujo = true;

    private Lock lock = new ReentrantLock();

    public void fazNumero1() {
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " batendo na porta");

        synchronized (this) {

            System.out.println(nome + " entrando no banheiro");

            if (this.ehSujo) {
                esperaLaFora(nome);
            }

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("entrando no banheiro");
            System.out.println("fazendo coisa rapida");

        }

        System.out.println("dando descarga");
        System.out.println("lavando a mao");
        System.out.println("saindo do banheiro");
    }

    public void fazNumero2() {
        String nome = Thread.currentThread().getName();

        System.out.println("entrando no banheiro");

        if (this.ehSujo) {
            esperaLaFora(nome);
        }

        System.out.println("fazendo coisa demorada");

        try {
            Thread.sleep(15000);
            this.ehSujo = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("dando descarga");
        System.out.println("lavando a mao");
        System.out.println("saindo do banheiro");
    }

    private void esperaLaFora(String nome) {

        System.out.println(nome + ", eca, banheiro está sujo");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void limpa() {

        String nome = Thread.currentThread().getName();

        System.out.println(nome + " batendo na porta");

        synchronized (this) {

            System.out.println(nome + " entrando no banheiro");

            if (!this.ehSujo) {
                System.out.println(nome + ", não está sujo, vou sair");
                return;
            }

            System.out.println(nome + " limpando o banheiro");
            this.ehSujo = false;

            try {
                Thread.sleep(13000);
                this.ehSujo = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.notifyAll();

            System.out.println(nome + " saindo do banheiro");
        }
    }
}