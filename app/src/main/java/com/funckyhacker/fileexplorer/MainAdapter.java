package com.funckyhacker.fileexplorer;

import android.content.ContentResolver;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.funckyhacker.fileexplorer.databinding.ItemMainLinearBinding;
import com.funckyhacker.fileexplorer.util.FileUtils;
import java.io.File;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

  private List<File> files;

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemMainLinearBinding linearBinding = ItemMainLinearBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(linearBinding);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(files.get(position), holder.binding.getRoot().getContext().getContentResolver());
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

    private final ItemMainLinearBinding binding;
    private File file;
    private ContentResolver resolver;

    public ViewHolder(ItemMainLinearBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    public void bind(File file, ContentResolver resolver) {
      this.file = file;
      this.resolver = resolver;
      binding.setFile(file);
      binding.setViewHolder(this);
      binding.executePendingBindings();
    }

    @DrawableRes public int getIconId() {
      switch (FileUtils.getMimeType(resolver, file)) {
      case "image/jpeg":
        return R.drawable.ic_jpg;
      case "application/directory":
        return R.drawable.ic_folder;
      case "application/pdf":
        return R.drawable.ic_pdf;
      case "audio/mp4":
      case "video/mp4":
      case "application/mp4":
        return R.drawable.ic_mp4;
      case "audio/mpeg":
        return R.drawable.ic_mp3;
      case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
        return R.drawable.ic_xls;
      case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
        return R.drawable.ic_doc;
      default:
        return R.drawable.ic_unknown;
      }
    }
  }
}
