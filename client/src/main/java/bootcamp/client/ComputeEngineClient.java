package bootcamp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ComputeEngineClient {

    private static final ObjectMapper JACKSON_OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;

        System.out.println("THE COMPUTE ENGINE");
        System.out.println("The compute engine will compute the frequency of each number in a list of numbers");
        System.out.println("For example the list: 1 2 3 1 2 2 3 will return the JSON string: {\"1\":2, \"2\":3, \"3\":2}.");
        System.out.println("\nEnter a list of number after the prompt seperated by a space\n");

        ClientResource cr = new ClientResource("http://localhost:8182/computeengine/aggregate");

        System.out.print(">");
        while ((line = input.readLine()) != null) {

            try {
                String[] tokens = line.trim().split("\\s+");

                List<Integer> integerList = Arrays.asList(tokens).stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                String jsonString = JACKSON_OBJECT_MAPPER.writeValueAsString(integerList);

                Representation rep = cr.post(new StringRepresentation(jsonString, MediaType.APPLICATION_JSON));

                if (MediaType.APPLICATION_JSON.equals(rep.getMediaType())) {
                    String jsonResult = rep.getText();
                    System.out.println("\n===========");
                    System.out.println("JSON Result");
                    System.out.println("===========");
                    System.out.println(jsonResult);
                } else {
                    System.out.println("Error: " + rep.getText());
                }
            }
            catch (Exception ex) {
                System.out.println("\nInput is malformed");
            }
            finally {
                System.out.print("\n>");
            }
        }
    }
}
