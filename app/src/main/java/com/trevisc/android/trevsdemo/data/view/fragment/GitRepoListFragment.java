package com.trevisc.android.trevsdemo.data.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import com.trevisc.android.trevsdemo.data.helper.GitHelper;
import com.trevisc.android.trevsdemo.data.model.Repo;
import com.trevisc.android.trevsdemo.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GitRepoListFragment extends Fragment {
    protected View root;
    //protected ProgressBar progressSpinner;
   // protected LinearLayout progressBar;
    protected List<Repo> repos;
    public GitRepoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_git_repo_list, container, false);
        new LoadFragmentTask().execute();
        return root;
    }

    protected class LoadFragmentTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
                       String accountName = "trevisc";
            if(params[0] != null) {
                accountName =  params[0].toString();
            }
                        if (repos == null) {
                         String uri =  getActivity().getResources().getString(R.string.github_rest_url);
                         repos = GitHelper.getGitRepos(uri,accountName);
//                            ((DataHolder) navActivity).setCachedTitles(titles);

                        }
                        //workListAdapter = new WorkListAdapter(context, R.layout.work_list_item, titles);
                return null;
            }

        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);
           /* try {
                if (entityListAdapter != null) {
                    ((TextView) root.findViewById(R.id.list_header_text)).setText(getString(R.string.select_group_heading));
                    showEntityList();
                    contentContainer.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                } else if (entity == null) {
                    progressSpinner.setVisibility(View.GONE);
                    progressText.setText(getString(R.string.no_entities));
                } else if (accountListAdapter != null) {
                    ((TextView) root.findViewById(R.id.list_header_text)).setText(getString(R.string.accounts_heading));
                    showAccountList();
                    contentContainer.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                } else if (account == null) {
                    progressSpinner.setVisibility(View.GONE);
                    progressText.setText(getString(R.string.no_accounts));
                } else if (workListAdapter != null) {
                    worksList.setAdapter(workListAdapter);
                    int workCount = workListAdapter.getCount();
                    ((TextView) root.findViewById(R.id.works_count)).setText(String.valueOf(workCount));
                    ((TextView) root.findViewById(R.id.account_name)).setText(account.getAccountName());
                    setCatalogListOnClicks();
                    showSearch();
                    root.findViewById(R.id.catalog_container).setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressSpinner.setVisibility(View.GONE);
                    progressText.setText(getString(R.string.no_titles));
                }
            } catch (IllegalStateException ise) {
                LOG.d(TAG, "activity was destroyed before the on process could complete");
            }
            if (((DataHolder) navActivity).getCurrentWorkId() > 0) {
                focusOnView();
            }*/
        }
    }
}
