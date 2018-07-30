package ru.spoddubnyak.elevator;

import java.util.Optional;

public class MenuElevator {

    class CallFromEntrance extends ActionsElevator {
        /**
         * Constructor of BaseAction.
         *
         * @param nameActions name of action to show in menu
         */
        public CallFromEntrance(String nameActions) {
            super(nameActions);
        }

        /**
         * property - number action.
         */
        private static final int ACTION = 1;

        @Override
        public void execute() {
            System.out.printf("1. Вызвать лифт на этаж из подъезад");
        }

        @Override
        public int key() {
            return ACTION;
        }
    }

    class CallFromElevator extends ActionsElevator {
        /**
         * Constructor of BaseAction.
         *
         * @param nameActions name of action to show in menu
         */
        public CallFromElevator(String nameActions) {
            super(nameActions);
        }

        /**
         * property - number action.
         */
        private static final int ACTION = 2;

        @Override
        public void execute() {
            System.out.printf("2. Нажать кнопку этажа внутри лифта");
        }

        @Override
        public int key() {
            return ACTION;
        }
    }

    public static void main(String[] args) {

    }



}
