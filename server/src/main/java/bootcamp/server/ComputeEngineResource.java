package bootcamp.server;

import bootcamp.computelibrary.ComputeEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ComputeEngineResource extends ServerResource{
    
    public static final String RESOURCE_URI="/computeengine/aggregate";
    private static final ObjectMapper JACKSON_OBJECT_MAPPER = new ObjectMapper();
    
    @Post("json")
    public Representation aggregate(Representation entity) {
        if (MediaType.APPLICATION_JSON.equals(entity.getMediaType())) {
            try {
                String jsonString = entity.getText();
                List<Integer> integerList = JACKSON_OBJECT_MAPPER.readValue(jsonString, List.class);
                Map<Integer, Long> computeResult = ComputeEngine.aggregate(integerList);

                String jsonResult = JACKSON_OBJECT_MAPPER.writeValueAsString(computeResult);
                return new StringRepresentation(jsonResult, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                return new StringRepresentation("Error Processing JSON string", MediaType.TEXT_PLAIN);
            }
        }
        else {
            return new StringRepresentation("Error Processing JSON string", MediaType.TEXT_PLAIN);
        }
    }
}
