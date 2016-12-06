package com.example.sks.recyclerviewanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sks.recyclerviewanim.animators.BaseItemAnimator;
import com.example.sks.recyclerviewanim.animators.FadeInAnimator;
import com.example.sks.recyclerviewanim.animators.FadeInDownAnimator;
import com.example.sks.recyclerviewanim.animators.FadeInLeftAnimator;
import com.example.sks.recyclerviewanim.animators.FadeInRightAnimator;
import com.example.sks.recyclerviewanim.animators.FadeInUpAnimator;
import com.example.sks.recyclerviewanim.animators.FlipInBottomXAnimator;
import com.example.sks.recyclerviewanim.animators.FlipInLeftYAnimator;
import com.example.sks.recyclerviewanim.animators.FlipInRightYAnimator;
import com.example.sks.recyclerviewanim.animators.FlipInTopXAnimator;
import com.example.sks.recyclerviewanim.animators.LandingAnimator;
import com.example.sks.recyclerviewanim.animators.OvershootInLeftAnimator;
import com.example.sks.recyclerviewanim.animators.OvershootInRightAnimator;
import com.example.sks.recyclerviewanim.animators.ScaleInAnimator;
import com.example.sks.recyclerviewanim.animators.ScaleInBottomAnimator;
import com.example.sks.recyclerviewanim.animators.ScaleInLeftAnimator;
import com.example.sks.recyclerviewanim.animators.ScaleInRightAnimator;
import com.example.sks.recyclerviewanim.animators.ScaleInTopAnimator;
import com.example.sks.recyclerviewanim.animators.SlideInDownAnimator;
import com.example.sks.recyclerviewanim.animators.SlideInLeftAnimator;
import com.example.sks.recyclerviewanim.animators.SlideInRightAnimator;
import com.example.sks.recyclerviewanim.animators.SlideInUpAnimator;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Wasabeef on 2015/01/03.
 */
public class AnimatorSampleActivity extends AppCompatActivity {

  enum Type {
    FadeIn(new FadeInAnimator(new OvershootInterpolator(1f))),
    FadeInDown(new FadeInDownAnimator(new OvershootInterpolator(1f))),
    FadeInUp(new FadeInUpAnimator(new OvershootInterpolator(1f))),
    FadeInLeft(new FadeInLeftAnimator(new OvershootInterpolator(1f))),
    FadeInRight(new FadeInRightAnimator(new OvershootInterpolator(1f))),
    Landing(new LandingAnimator(new OvershootInterpolator(1f))),
    ScaleIn(new ScaleInAnimator(new OvershootInterpolator(1f))),
    ScaleInTop(new ScaleInTopAnimator(new OvershootInterpolator(1f))),
    ScaleInBottom(new ScaleInBottomAnimator(new OvershootInterpolator(1f))),
    ScaleInLeft(new ScaleInLeftAnimator(new OvershootInterpolator(1f))),
    ScaleInRight(new ScaleInRightAnimator(new OvershootInterpolator(1f))),
    FlipInTopX(new FlipInTopXAnimator(new OvershootInterpolator(1f))),
    FlipInBottomX(new FlipInBottomXAnimator(new OvershootInterpolator(1f))),
    FlipInLeftY(new FlipInLeftYAnimator(new OvershootInterpolator(1f))),
    FlipInRightY(new FlipInRightYAnimator(new OvershootInterpolator(1f))),
    SlideInLeft(new SlideInLeftAnimator(new OvershootInterpolator(1f))),
    SlideInRight(new SlideInRightAnimator(new OvershootInterpolator(1f))),
    SlideInDown(new SlideInDownAnimator(new OvershootInterpolator(1f))),
    SlideInUp(new SlideInUpAnimator(new OvershootInterpolator(1f))),
    OvershootInRight(new OvershootInRightAnimator(1.0f)),
    OvershootInLeft(new OvershootInLeftAnimator(1.0f));

    private BaseItemAnimator mAnimator;

    Type(BaseItemAnimator animator) {
      mAnimator = animator;
    }

    public BaseItemAnimator getAnimator() {
      return mAnimator;
    }
  }

  private static String[] data = new String[] {
      "Apple", "Ball", "Camera", "Day", "Egg", "Foo", "Google", "Hello", "Iron", "Japan", "Coke",
      "Dog", "Cat", "Yahoo", "Sony", "Canon", "Fujitsu", "USA", "Nexus", "LINE", "Haskell", "C++",
      "Java", "Go", "Swift", "Objective-c", "Ruby", "PHP", "Bash", "ksh", "C", "Groovy", "Kotlin"
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animator_sample);

    Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

    if (getIntent().getBooleanExtra("GRID", true)) {
      recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    } else {
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    recyclerView.setItemAnimator(new SlideInLeftAnimator());

    final MainAdapter adapter = new MainAdapter(this, new ArrayList<>(Arrays.asList(data)));
    recyclerView.setAdapter(adapter);

    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    ArrayAdapter<String> spinnerAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    for (Type type : Type.values()) {
      spinnerAdapter.add(type.name());
    }
    spinner.setAdapter(spinnerAdapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        recyclerView.setItemAnimator(Type.values()[position].getAnimator());
        recyclerView.getItemAnimator().setAddDuration(500);
        recyclerView.getItemAnimator().setRemoveDuration(500);
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        adapter.add("newly added item", 1);
      }
    });

    findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        adapter.remove(1);
      }
    });
  }
}
