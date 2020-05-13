package com.practicaltest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.database.AppDatabase;
import com.database.User;
import com.utils.Validator;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private Button btnSaveData, btnShowList, btnServiceCall;
    private EditText editName, editPhone, editAddress, editEmail, editPassword, editConfirmPassword;
    private RadioGroup rbGroupGender;
    private String imagePath = "", selectedGender;
    private ImageView ivProfilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        checkStringAlphaNumeric();
    }

    private void checkStringAlphaNumeric() {
        try {
            String s = "1230456987a";
            boolean b = false;
            int charCount = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ((int) c >= 48 && (int) c <= 57) {
                    ++charCount;
                }
            }
            if (charCount == s.length()) {
                Log.e("result ", "Numeric");
            } else {
                Log.e("result ", "Not Numeric");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViews() {
        try {
            editName = findViewById(R.id.editName);
            editPhone = findViewById(R.id.editPhone);
            editAddress = findViewById(R.id.editAddress);
            editEmail = findViewById(R.id.editEmail);
            editPassword = findViewById(R.id.editPassword);
            editConfirmPassword = findViewById(R.id.editConfirmPassword);

            ivProfilePic = findViewById(R.id.ivProfilePic);
            ivProfilePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isStoragePermissionGranted()) {
                        openGallery();
                    }
                }
            });
            selectedGender = getString(R.string.male);
            rbGroupGender = findViewById(R.id.rbGroupGender);
            rbGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rbFemale) {
                        selectedGender = getString(R.string.female);
                    } else {
                        selectedGender = getString(R.string.male);
                    }
                }
            });

            btnSaveData = findViewById(R.id.btnSaveData);
            btnSaveData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (validateData()) {
                        try {
                            Log.v("Name ", editName.getText().toString());
                            Log.v("Phone ", editPhone.getText().toString());
                            Log.v("Address ", editAddress.getText().toString());
                            Log.v("Profile Pic ", imagePath);
                            Log.v("Password ", editPassword.getText().toString());
                            Log.v("Email ", editEmail.getText().toString());
                            Log.v("Gender ", selectedGender);
                            new SaveDataInDB(MainActivity.this).execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                    }
                }
            });
            btnShowList = findViewById(R.id.btnShowList);
            btnShowList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), RecyclerListLocalDB.class));
                }
            });
            btnServiceCall = findViewById(R.id.btnServiceCall);
            btnServiceCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), RecyclerListServiceCallUsingVolley.class));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalFilesDir("");
        if (getImage != null) {
            File imagePath = new File(getImage, "images");
            if (!imagePath.exists()) {
                try {
                    imagePath.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            File newFile = new File(imagePath, "profile.jpg");
            outputFileUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", newFile);
        }
        return outputFileUri;
    }

    private void openGallery() {
//        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(i, 1000);

        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if (outputFileUri != null) {
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        }
        startActivityForResult(captureIntent, IMAGE_RESULT);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            //
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                return false;
            }
        } else {
            return true;
        }
    }

//
//    private void askForMediaPermissionsCamera() {
//        int hasMediaPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
//        if (hasMediaPermission != PackageManager.PERMISSION_GRANTED) {
//            if (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Utils.getInstance().showAlertForPermission(getContext(), getString(R.string.access_read_external_storage_message), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, ConfigConstants.PermissionCodes.REQUEST_PERMISSION_GALLERY_CAMERA);
//                    }
//                });
//                return;
//            }
//            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, ConfigConstants.PermissionCodes.REQUEST_PERMISSION_GALLERY_CAMERA);
//            return;
//        }
//        askForCameraPermissions();
//    }
//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1000 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                ivProfilePic.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                cursor.close();
            }
            if (requestCode == IMAGE_RESULT) {
                String filePath = getImageFilePath(data);
                if (filePath != null) {
                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                    ivProfilePic.setImageBitmap(selectedImage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static int IMAGE_RESULT = 200;

    private String getImageFromFilePath(Intent data) {
        boolean isCamera = data == null || data.getData() == null;
        if (isCamera) {
            return getCaptureImageOutputUri().getPath();
        } else {
            return getPathFromURI(data.getData());
        }

    }

    public String getImageFilePath(Intent data) {
        return getImageFromFilePath(data);
    }

    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private boolean validateData() {
        if (Validator.isEmptyString(imagePath)) {
            Toast.makeText(getContext(), "Select Profile Pic", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Validator.isEmptyString(editName.getText().toString())) {
            editName.setError("Enter Name");
            editName.requestFocus();
            return false;
        }
        if (Validator.isEmptyString(editPhone.getText().toString())) {
            editPhone.setError("Enter Phone Number");
            editPhone.requestFocus();
            return false;
        }
        if (!Validator.isPhoneNumberValid(editPhone.getText().toString())) {
            editPhone.setError("Invalid Phone Number");
            editPhone.requestFocus();
            return false;
        }
        if (Validator.isEmptyString(editAddress.getText().toString())) {
            editAddress.setError("Enter Address");
            editAddress.requestFocus();
            return false;
        }
        if (Validator.isEmptyString(editEmail.getText().toString())) {
            editEmail.setError("Enter Email");
            editEmail.requestFocus();
            return false;
        }
        if (!Validator.isEmailValid(editEmail.getText().toString())) {
            editEmail.setError("Invalid Email");
            editEmail.requestFocus();
            return false;
        }
        if (Validator.isEmptyString(editPassword.getText().toString())) {
            editPassword.setError("Enter Password");
            editPassword.requestFocus();
            return false;
        }
        if (Validator.isEmptyString(editConfirmPassword.getText().toString())) {
            editConfirmPassword.setError("Enter Confirm Password");
            editConfirmPassword.requestFocus();
            return false;
        }
        if (!editPassword.getText().toString().equals(editConfirmPassword.getText().toString())) {
            editConfirmPassword.setError("Password And Confirm Password Does Not Match!");
            editConfirmPassword.requestFocus();
            return false;
        }
        return true;
    }

    private Context getContext() {
        return this;
    }

    private class SaveDataInDB extends AsyncTask<Void, Void, Void> {
        //Prevent leak
        private WeakReference<Activity> weakActivity;

        User user;

        public SaveDataInDB(Activity activity) {
            weakActivity = new WeakReference<>(activity);
            user = new User();
            user.name = editName.getText().toString();
            user.address = editAddress.getText().toString();
            user.phoneNumber = editPhone.getText().toString();
            user.password = editPassword.getText().toString();
            user.email = editEmail.getText().toString();
            user.gender = selectedGender;
            user.image = imagePath;
        }

        @Override
        protected Void doInBackground(Void... params) {

            AppDatabase.getInstance(getContext()).userDao().insertAll(user);
            Log.v("Data ", "Inserted");
            return null;
        }

        @Override
        protected void onPostExecute(Void agentsCount) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return;
            }
            editName.setText("");
            editAddress.setText("");
            editPhone.setText("");
            editPassword.setText("");
            editEmail.setText("");
            editConfirmPassword.setText("");
            selectedGender = "";
            imagePath = "";

            ivProfilePic.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));

        }
    }
}
