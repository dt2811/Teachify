package com.example.firebaseauth;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class addfrag extends Fragment {
    private String dbVideoURI;
    int flag = -1;
    private String dbImageURI;

    private static final int GET_VIDEO_REQUEST = 1010;//
    private static final int GET_IMAGE_REQUEST = 1111;//


    int c = 0;
    EditText e1, e2, e3;
    TextView titleAdd, videoLink, imageLink;
    EditText title, description;
    Button b1, b2, submitButton;
    String ntname;
    View v;
    HashMap<String, String> q = new HashMap<>();
    String cTitle, cDescription;

    private Uri mVideoUri = null;//
    private Uri mImageUri = null;//
    private Uri commonUri;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();//firebase instantiated

    //reference to storage
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("" + "Java");

//    DocumentReference currentUser = db.collection("Courses")
//            .document("" + "Java");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.add_frag, container, false);
        e1 = v.findViewById(R.id.name);//

        e2 = v.findViewById(R.id.questions);

        e3 = v.findViewById(R.id.answers);
        b1 = v.findViewById(R.id.Nextbutton);
        b2 = v.findViewById(R.id.Donebutton);
        titleAdd = v.findViewById(R.id.Titleadd);

        submitButton = v.findViewById(R.id.submit_button);

        videoLink = v.findViewById(R.id.video_link);//
        imageLink = v.findViewById(R.id.image_link);//

        title = v.findViewById(R.id.course_name);
        description = v.findViewById(R.id.course_desc);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleAdd.setVisibility(View.VISIBLE);
                e1.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                e3.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.INVISIBLE);
                videoLink.setVisibility(View.INVISIBLE);
                imageLink.setVisibility(View.INVISIBLE);
                title.setVisibility(View.INVISIBLE);
                description.setVisibility(View.INVISIBLE);

                cTitle = title.getText().toString();
                cDescription = description.getText().toString();
                funcTest();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!e1.getText().toString().isEmpty()) {
                    ntname = e1.getText().toString();
                    e1.setVisibility(View.INVISIBLE);
                    if (e2.getText().toString().isEmpty() || e3.getText().toString().isEmpty()) {
                        Toast.makeText(v.getContext(), "Oops you left field empty", Toast.LENGTH_SHORT).show();
                    } else {
                        c+=1;
                        String questions = "q" + String.valueOf(c);
                        q.put(questions, e2.getText().toString());
                        String answer = "a" + String.valueOf(c);
                        q.put(answer, e3.getText().toString());
                        e1.setText(" ");
                        e2.setText(" ");
                        Toast.makeText(v.getContext(), " SAVED ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                FirebaseFirestore fb = FirebaseFirestore.getInstance();
                fb.collection("Test").document(cTitle).set(q).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Test Saved", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(v.getContext(), " Launched", Toast.LENGTH_SHORT).show();
                //getFragmentManager().beginTransaction().replace(R.layout.home_layout, fragholder).commit();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getVideoFromGallery();
            }
        });

        //Image Button
       imageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromGallery();
            }
        });
    }

    private void getVideoFromGallery() {
        Intent imgIntent = new Intent();
        imgIntent.setType("video/*");
        imgIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(imgIntent, GET_VIDEO_REQUEST);
    }
    private void getImageFromGallery() {
        Intent imgIntent = new Intent();
        imgIntent.setType("image/*");
        imgIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(imgIntent, GET_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = Objects.requireNonNull(getActivity()).getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_IMAGE_REQUEST || requestCode == GET_VIDEO_REQUEST || resultCode == RESULT_OK || data != null || data.getData() != null) {
            if (requestCode == GET_VIDEO_REQUEST) {
                mVideoUri = data.getData();
                uploadToStorage();
            }
            if  (requestCode == GET_IMAGE_REQUEST) {
                mImageUri = data.getData();
                uploadToStorage();
                Toast.makeText(getContext(), mImageUri.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void uploadToStorage()
    {
        if(mVideoUri!=null && c == 0)
        {
            c += 1;
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mVideoUri));
            fileReference.putFile(mVideoUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //download URI

                                        dbVideoURI = uri.toString();//
                                        Toast.makeText(getActivity(), "Update successful", Toast.LENGTH_LONG).show();

//                                        dbImageURI = uri.toString();
//                                    // funcTest();
//                                    Toast.makeText(getActivity(), "Update successful", Toast.LENGTH_LONG).show();

                                    //funcTest();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Update failed :(", e.toString() + "");
                                    Toast.makeText(getActivity(), "Oops :(", Toast.LENGTH_SHORT).show();
                                }
                            });


//                            saveDataLocally(1);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Error", e.toString());
                    Toast.makeText(getActivity(), "Oops :(", Toast.LENGTH_SHORT).show();
                }
            });}
            else if (mImageUri != null && c == 1)
            {
                c = 0;
                final StorageReference fileReference1 = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
                fileReference1.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            fileReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //download URI


                                        dbImageURI = uri.toString();
                                        // funcTest();
                                        Toast.makeText(getActivity(), "Update successful", Toast.LENGTH_LONG).show();

                                    //funcTest();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Update failed :(", e.toString() + "");
                                    Toast.makeText(getActivity(), "Oops :(", Toast.LENGTH_SHORT).show();
                                }
                            });


//                            saveDataLocally(1);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Error", e.toString());
                    Toast.makeText(getActivity(), "Oops :(", Toast.LENGTH_SHORT).show();
                }
            });}

//            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    getmImageUri = uri;
//                }
//            });

        }

    private void funcTest() {


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("description", cDescription);
        hashMap.put("name", cTitle);
        hashMap.put("video_url", dbVideoURI);
        hashMap.put("img_url", dbImageURI);

        db.collection("Courses").document(cTitle).set(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Uploaded to Firestore!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Oops,something went wrong!!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
