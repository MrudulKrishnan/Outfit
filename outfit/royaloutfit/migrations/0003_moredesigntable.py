# Generated by Django 3.2.20 on 2024-03-18 03:58

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('royaloutfit', '0002_chat'),
    ]

    operations = [
        migrations.CreateModel(
            name='MoreDesignTable',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('design_more', models.FloatField()),
                ('DESIGN', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='royaloutfit.designers_table')),
            ],
        ),
    ]