package com.github.kylerequez.SpringFirebaseToDo.Todo;

import com.github.kylerequez.SpringFirebaseToDo.Exceptions.DocumentDoesNotExistException;
import com.github.kylerequez.SpringFirebaseToDo.Exceptions.DocumentExistsException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.github.kylerequez.SpringFirebaseToDo.Configuration.ConfigurationStrings.COLUMN_NAME;

@Service
public class TodoServiceImpl implements TodoService{
    public ResponseEntity<List<Todo>> getAllTodoDetails() throws InterruptedException, ExecutionException {
       Firestore dbFirestore = FirestoreClient.getFirestore();

       ApiFuture<QuerySnapshot> future = dbFirestore.collection(COLUMN_NAME).get();

       List<QueryDocumentSnapshot> todos = future.get().getDocuments();
       return ResponseEntity.ok(
               todos
                       .stream()
                       .map(
                       todo -> todo.toObject(Todo.class)
               )
                       .collect(Collectors.toList())
       );
    }

    public ResponseEntity<Todo> getTodoDetailsByName(String title) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLUMN_NAME).document(title);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(!document.exists()) {
            throw new DocumentDoesNotExistException("The todo titled \"" + title + "\" does not exist.");
        }
        return ResponseEntity.ok(document.toObject(Todo.class));
    }

    public ResponseEntity<Todo> saveTodoDetails(Todo todo) throws InterruptedException, ExecutionException  {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection(COLUMN_NAME).document(todo.getTitle());
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        System.out.println(document.toObject(Todo.class));
        if(document.exists()) {
            throw new DocumentExistsException(HttpStatus.CONFLICT, "The document title already exists! Please try again", document.toObject(Todo.class));
        }

        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLUMN_NAME).document(todo.getTitle()).set(todo);
        return ResponseEntity.ok(todo);
    }

    public ResponseEntity<Todo> updateTodoDetails(String title, Todo todo) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection(COLUMN_NAME).document(title);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if(!document.exists()) {
            throw new DocumentDoesNotExistException("The todo titled \"" + title + "\" does not exist.");
        }

        HashMap<String, Object> updates = new HashMap<String, Object>();

        updates.put("title", todo.getTitle());
        updates.put("description", todo.getDescription());
        updates.put("status", todo.getStatus());
        updates.put("createdAt", todo.getCreatedAt());
        updates.put("updatedAt", new Date());

        documentReference.update(updates);
        return ResponseEntity.ok(todo);
    }
}
