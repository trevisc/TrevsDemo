package com.trevisc.android.trevsdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.trevisc.android.trevsdemo.data.helper.GitHelper;
import com.trevisc.android.trevsdemo.data.model.Repo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText gitId;
    private TextView finalResult;
    private List<Repo> repos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gitId = (EditText) findViewById(R.id.in_time);
        button = (Button) findViewById(R.id.btn_run);
        finalResult = (TextView) findViewById(R.id.tv_result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String userId = gitId.getText().toString().trim().toLowerCase();
                if(userId.trim().length() == 0){
                    finalResult.setText(getString(R.string.blank_gitid));
                    return;
                }
                runner.execute(userId);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        private String resp;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Getting Git Repos..."); // Calls onProgressUpdate()
            try {
                String uri = getString(R.string.github_rest_url) +  params[0].toString() + "/repos";
                resp = GitHelper.getGitReposJSON(uri);
                //resp = "Slept for " + params[0] + " seconds";
            }  catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation

            progressDialog.dismiss();
            if(result == null){
                finalResult.setText(getString(R.string.no_repos));
                return;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            List<Repo> repos = new ArrayList<>();
            try {

                JsonNode node = objectMapper.readValue(result, JsonNode.class);
                if(node != null) {

                    if (node.isArray()) {
                        ArrayNode arrayNode = objectMapper.createArrayNode();
                        Iterator<JsonNode> elementsIterator = ((ArrayNode) node).elements();
                        while (elementsIterator.hasNext()){
                            Repo thisRepo = new Repo();
                            JsonNode thsnode = elementsIterator.next();
                            thisRepo.setId(thsnode.get("id").asLong());
                            thisRepo.setDescription(thsnode.get("name").asText());
                            thisRepo.setUrl(thsnode.get("url").asText());
                            repos.add((thisRepo));
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("error");
            }
            finalResult.setText("Load Complete");
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Accessing GITHub API","Retrieving Repository List"
                   );
        }


        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);

        }
    }
}
