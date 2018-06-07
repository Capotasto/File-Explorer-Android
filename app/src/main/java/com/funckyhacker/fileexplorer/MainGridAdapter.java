package com.funckyhacker.fileexplorer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.funckyhacker.fileexplorer.databinding.ItemMainGridBinding;
import com.funckyhacker.fileexplorer.event.ClickItemEvent;
import com.funckyhacker.fileexplorer.util.DateUtils;
import java.io.File;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class MainGridAdapter extends RecyclerView.Adapter<MainGridAdapter.ViewHolder> {

  private List<File> files;

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemMainGridBinding gridBinding = ItemMainGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(gridBinding);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(files.get(position));
    holder.binding.getRoot().setOnClickListener(v -> {
      File file = files.get(position);
      EventBus.getDefault().post(new ClickItemEvent(file));
    });
  }

  @Override public int getItemCount() {
    if (files == null) {
      return 0;
    }
    return files.size();
  }

  public void setData(List<File> files) {
    this.files = files;
    notifyDataSetChanged();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private final ItemMainGridBinding binding;
    private File file;

    public ViewHolder(ItemMainGridBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    public void bind(File file) {
      this.file = file;
      binding.setFile(file);
      binding.setViewHolder(this);
      binding.executePendingBindings();
    }

    public String getModified() {
      return DateUtils.getItemDate(file.lastModified());
    }
  }
}