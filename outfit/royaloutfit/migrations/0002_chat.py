# Generated by Django 3.2.23 on 2024-01-08 05:41

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('royaloutfit', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='chat',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('msg', models.TextField()),
                ('date', models.DateField()),
                ('fromid', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='fid', to='royaloutfit.login_table')),
                ('toid', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='tid', to='royaloutfit.login_table')),
            ],
        ),
    ]
