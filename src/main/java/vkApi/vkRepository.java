package vkApi;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.base.City;
import com.vk.api.sdk.objects.groups.UserXtrRole;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import models.Student;

import java.util.ArrayList;
import java.util.Optional;

public class vkRepository {
    private final int APP_ID = 0;
    private final String CODE = "";
    private final VkApiClient vk;
    private final UserActor actor;
    private static final String GROUP_ID = "basicprogrammingrtf2022";
    private static final int API_LIMIT = 1000;


    public vkRepository() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public void setCitiesToStudents(ArrayList<Student> students) {
        ArrayList<UserXtrRole> subscribedMembers = getSubscribedStudent();
        for(Student student: students){
            String[] name = student.getFullName().split(" ", 2);
            if (name.length != 2) {
                continue;
            }
            Optional<UserXtrRole> studentFromGroup = subscribedMembers.stream()
                    .filter(member -> member.getLastName().equals(name[0]))
                    .filter(member -> member.getFirstName().equals(name[1]))
                    .findFirst();
            if (studentFromGroup.isEmpty()) {
                continue;
            }
            City city = studentFromGroup.get().getCity();
            student.setCity(city == null ? student.getCity(): city.getTitle());
        }
    }

    private ArrayList<UserXtrRole> getSubscribedStudent() {
        int offset = 0;
        ArrayList<UserXtrRole> members = new ArrayList<>();
        try {
            while (true) {
                var membersPart = vk.groups()
                        .getMembersWithFields(actor, Fields.CITY)
                        .groupId(GROUP_ID)
                        .offset(offset)
                        .execute()
                        .getItems();
                offset += API_LIMIT;
                members.addAll(membersPart);
                if (offset > membersPart.size()) {
                    break;
                }
            }
        } catch (ApiException | ClientException e) {
            throw new RuntimeException(e);
        }
        return members;
    }
}
