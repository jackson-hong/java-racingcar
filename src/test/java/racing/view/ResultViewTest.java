package racing.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racing.core.Car;
import racing.core.CarList;
import racing.core.Mover;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    ResultView resultView = new ResultView();

    @BeforeEach
    void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("첫 메세지를 정상적으로 출력하는지 확인")
    void testCorrectFirstText() {
        resultView.printStartText();

        assertThat(outContent.toString()).isEqualTo("\n실행 결과\n");
    }

    @Test
    @DisplayName("CarList가 주어졌을 때 정상적인 출력값 확인")
    void testPrintedTextWithCarList() {
        Car car1 = new Car("jack");
        Car car2 = new Car("jason");
        Car car3 = new Car("eric");
        Mover mover = new Mover();
        mover.decideMove(car1, 4);
        mover.decideMove(car2, 5);
        mover.decideMove(car2, 7);
        mover.decideMove(car3, 3);
        List<Car> carList = Arrays.asList(car1, car2, car3);
        CarList cars = new CarList(carList);

        resultView.printCarList(cars);

        assertThat(outContent.toString()).isEqualTo("jack : --\njason : ---\neric : -\n\n");
    }

    @Test
    @DisplayName("크기가 1보다 큰 WinnerList가 주어졌을 때 정상 출력값 확인")
    void testPrintedTextWithWinners(){
        Car car1 = new Car("jack");
        Car car2 = new Car("jason");
        List<Car> winners = Arrays.asList(car1, car2);

        resultView.printWinners(new CarList(winners));

        assertThat(outContent.toString()).isEqualTo("jack, jason가 최종 우승했습니다.");
    }

    @Test
    @DisplayName("크기가 1인 큰 WinnerList가 주어졌을 때 정상 출력값 확인")
    void testPrintedTextWithWinner(){
        Car car1 = new Car("jack");
        List<Car> winners = Arrays.asList(car1);

        resultView.printWinners(new CarList(winners));

        assertThat(outContent.toString()).isEqualTo("jack가 최종 우승했습니다.");
    }

}
